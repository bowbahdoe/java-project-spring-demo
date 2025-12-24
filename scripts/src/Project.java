import dev.mccue.tools.ExitStatusException;
import dev.mccue.tools.Tool;
import dev.mccue.tools.jar.Jar;
import dev.mccue.tools.java.Java;
import dev.mccue.tools.javac.Javac;
import dev.mccue.tools.javadoc.Javadoc;
import dev.mccue.tools.junit.JUnitArguments;
import picocli.CommandLine;
import module org.apache.commons.io;

import java.nio.file.Path;

@CommandLine.Command(
        name = "project"
)
public final class Project {
    private static final Path BUILD = Path.of("build");

    public static void main(String[] args) {
        new CommandLine(new Project()).execute(args);
    }

    @CommandLine.Command(name = "clean")
    public void clean() throws Exception {
        System.err.println("rm -rf " + BUILD);
        FileUtils.deleteDirectory(BUILD.toFile());
    }

    @CommandLine.Command(name = "compile")
    public void compile() throws Exception {
        clean();
        Javac.run(arguments -> {
            arguments
                    ._d(BUILD.resolve("javac"))
                    .argumentFile(Path.of("dependencySets", "default"))
                    .__module_source_path("./modules/*/src")
                    .__module("web.hello", "web.util", "web.hello.test", "web.util.test");
        });
    }


    @CommandLine.Command(name = "package")
    public void package_() throws Exception {
        compile();
        Jar.run(arguments -> {
            arguments.__create()
                    .__file(BUILD.resolve("jar", "web.hello.jar"))
                    .__main_class("web.hello.Application")
                    ._C(Path.of("modules/web.hello/res"), ".")
                    ._C(BUILD.resolve("javac", "web.hello"), ".");
        });

        Jar.run(arguments -> {
            arguments.__create()
                    .__file(Path.of("build/jar/web.util.jar"))
                    ._C(BUILD.resolve("javac", "web.util"), ".");
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
    public void document() throws Exception {
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
            arguments.add("@" + Path.of("dependencySets", "test"));
            arguments.__add_modules("web.hello.test", "web.util.test")
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
    public void run() throws Exception {
        Java.run(arguments -> {
            arguments.add("@" + Path.of("dependencySets", "runtime"));
            arguments
                    .__module("web.hello");
        });
    }
}
