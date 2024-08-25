import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;
import dev.mccue.tools.jar.Jar;
import dev.mccue.tools.java.Java;
import dev.mccue.tools.javac.Javac;
import dev.mccue.tools.javadoc.Javadoc;
import dev.mccue.tools.jresolve.JResolve;
import dev.mccue.tools.junit.JUnitArguments;
import picocli.CommandLine;

import java.nio.file.Path;

@CommandLine.Command(
        name = "project"
)
public final class Project {
    public static void main(String[] args) {
        new CommandLine(new Project()).execute(args);
    }

    @CommandLine.Command(name = "install")
    public void install() throws ExitStatusException {
        JResolve.run(arguments -> {
            arguments
                    .__purge_output_directory()
                    .__use_module_names()
                    .__output_directory("libs")
                    .argumentFile("libs.txt");
        });
    }

    @CommandLine.Command(name = "clean")
    public void clean() throws ExitStatusException {
        Tool.ofSubprocess("rm")
                .run("-rf", "build");
    }

    @CommandLine.Command(name = "compile")
    public void compile() throws ExitStatusException {
        clean();
        Javac.run(arguments -> {
            arguments
                    ._d(Path.of("build/javac"))
                    .__module_path("libs")
                    .__module_source_path("./modules/*/src")
                    .__module("web.hello", "web.util", "web.hello.test", "web.util.test");
        });
    }


    @CommandLine.Command(name = "package")
    public void package_() throws ExitStatusException {
        compile();
        Jar.run(arguments -> {
            arguments.__create()
                    .__file(Path.of("build/jar/web.hello.jar"))
                    .__main_class("web.hello.Application")
                    ._C(Path.of("modules/web.hello/res"), ".")
                    ._C(Path.of("build/javac/web.hello"), ".");
        });

        Jar.run(arguments -> {
            arguments.__create()
                    .__file(Path.of("build/jar/web.util.jar"))
                    ._C(Path.of("build/javac/web.util"), ".");
        });

        Jar.run(arguments -> {
            arguments.__create()
                    .__file(Path.of("build/jar/web.hello.test.jar"))
                    ._C(Path.of("build/javac/web.hello.test"), ".");
        });

        Jar.run(arguments -> {
            arguments.__create()
                    .__file(Path.of("build/jar/web.util.test.jar"))
                    ._C(Path.of("build/javac/web.util.test"), ".");
        });
    }

    @CommandLine.Command(name = "document")
    public void document() throws ExitStatusException {
        Javadoc.run(arguments -> {
            arguments._d(Path.of("build/javadoc"))
                    .__module_path("libs")
                    .__module_source_path("./modules/*/src")
                    .__module("web.hello", "web.util");
        });
    }

    @CommandLine.Command(name = "test")
    public void test() throws Exception {
        package_();
        Java.run(arguments -> {
            arguments.__module_path("libs", "build/jar")
                    .__add_modules("web.hello.test", "web.util.test")
                    .__module("org.junit.platform.console")
                    .addAll(
                            new JUnitArguments()
                                    .execute()
                                    .__disable_banner()
                                    .__select_module("web.hello.test")
                                    .__select_module("web.util.test")
                                    .__reports_dir(Path.of("build/junit"))
                    );
        });
    }


    @CommandLine.Command(name = "run")
    public void run() throws ExitStatusException {
        Java.run(arguments -> {
            arguments
                    .__module_path(
                            Path.of("libs"),
                            Path.of("build/jar")
                    )
                    .__module("web.hello");
        });
    }
}
