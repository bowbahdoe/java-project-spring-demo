import dev.mccue.tools.ToolArguments;

import java.util.Collection;

public final class JUnitArguments extends ToolArguments {
    static String toArgumentString(Object o) {
        return o == null ? "" : o.toString();
    }

    public JUnitArguments() {}

    public JUnitArguments(Collection<? extends String> c) {
        super(c);
    }

    public JUnitArguments discover() {
        add("discover");
        return this;
    }

    public JUnitArguments execute() {
        add("execute");
        return this;
    }

    public JUnitArguments engines() {
        add("engines");
        return this;
    }

    // Usage: junit execute [OPTIONS]
    //Execute tests
    //      [@<filename>...]       One or more argument files containing options.
    //      --disable-banner       Disable print out of the welcome message.
    //      --disable-ansi-colors  Disable ANSI colors in output (not supported by all terminals).
    //  -h, --help                 Display help information.
    //
    //SELECTORS
    //
    //      --scan-classpath, --scan-class-path[=PATH]
    //                             Scan all directories on the classpath or explicit classpath roots. Without arguments, only directories on the system classpath as well as additional classpath entries supplied
    //                               via -cp (directories and JAR files) are scanned. Explicit classpath roots that are not on the classpath will be silently ignored. This option can be repeated.
    //      --scan-modules         Scan all resolved modules for test discovery.
    //  -u, --select-uri=URI       Select a URI for test discovery. This option can be repeated.
    //  -f, --select-file=FILE     Select a file for test discovery. This option can be repeated.
    //  -d, --select-directory=DIR Select a directory for test discovery. This option can be repeated.
    //  -o, --select-module=NAME   Select single module for test discovery. This option can be repeated.
    public JUnitArguments _o(Object name) {
        add("-o");
        add(toArgumentString(name));
        return this;
    }

    public JUnitArguments __select_module(Object name) {
        add("--select-module");
        add(toArgumentString(name));
        return this;
    }

    //  -p, --select-package=PKG   Select a package for test discovery. This option can be repeated.
    //  -c, --select-class=CLASS   Select a class for test discovery. This option can be repeated.
    //  -m, --select-method=NAME   Select a method for test discovery. This option can be repeated.
    //  -r, --select-resource=RESOURCE
    //                             Select a classpath resource for test discovery. This option can be repeated.
    //  -i, --select-iteration=TYPE:VALUE[INDEX(..INDEX)?(,INDEX(..INDEX)?)*]
    //                             Select iterations for test discovery (e.g. method:com.acme.Foo#m()[1..2]). This option can be repeated.
    //
    //FILTERS
    //
    //  -n, --include-classname=PATTERN
    //                             Provide a regular expression to include only classes whose fully qualified names match. To avoid loading classes unnecessarily, the default pattern only includes class names that
    //                               begin with "Test" or end with "Test" or "Tests". When this option is repeated, all patterns will be combined using OR semantics. Default: ^(Test.*|.+[.$]Test.*|.*Tests?)$
    //  -N, --exclude-classname=PATTERN
    //                             Provide a regular expression to exclude those classes whose fully qualified names match. When this option is repeated, all patterns will be combined using OR semantics.
    //      --include-package=PKG  Provide a package to be included in the test run. This option can be repeated.
    //      --exclude-package=PKG  Provide a package to be excluded from the test run. This option can be repeated.
    //  -t, --include-tag=TAG      Provide a tag or tag expression to include only tests whose tags match. When this option is repeated, all patterns will be combined using OR semantics.
    //  -T, --exclude-tag=TAG      Provide a tag or tag expression to exclude those tests whose tags match. When this option is repeated, all patterns will be combined using OR semantics.
    //  -e, --include-engine=ID    Provide the ID of an engine to be included in the test run. This option can be repeated.
    //  -E, --exclude-engine=ID    Provide the ID of an engine to be excluded from the test run. This option can be repeated.
    //
    //RUNTIME CONFIGURATION
    //
    //      -cp, --classpath, --class-path=PATH
    //                             Provide additional classpath entries -- for example, for adding engines and their dependencies. This option can be repeated.
    //      --config=KEY=VALUE     Set a configuration parameter for test discovery and execution. This option can be repeated.
    //
    //CONSOLE OUTPUT
    //
    //      --color-palette=FILE   Specify a path to a properties file to customize ANSI style of output (not supported by all terminals).
    //      --single-color         Style test output using only text attributes, no color (not supported by all terminals).
    //      --details=MODE         Select an output details mode for when tests are executed. Use one of: none, summary, flat, tree, verbose, testfeed. If 'none' is selected, then only the summary and test
    //                               failures are shown. Default: tree.
    //      --details-theme=THEME  Select an output details tree theme for when tests are executed. Use one of: ascii, unicode. Default is detected based on default character encoding.
    //
    //REPORTING
    //
    //      --fail-if-no-tests     Fail and return exit status code 2 if no tests are found.
    //      --reports-dir=DIR      Enable report output into a specified local directory (will be created if it does not exist).
    public JUnitArguments __reports_dir(Object dir) {
        add("--reports-dir");
        add(toArgumentString(dir));
        return this;
    }

    // -------------

    // Usage: junit discover [OPTIONS]
    //Discover tests
    //      [@<filename>...]       One or more argument files containing options.
    //      --disable-banner       Disable print out of the welcome message.
    //      --disable-ansi-colors  Disable ANSI colors in output (not supported by all terminals).
    //  -h, --help                 Display help information.
    //
    //SELECTORS
    //
    //      --scan-classpath, --scan-class-path[=PATH]
    //                             Scan all directories on the classpath or explicit classpath roots. Without arguments, only directories on the system classpath as well as additional classpath entries supplied
    //                               via -cp (directories and JAR files) are scanned. Explicit classpath roots that are not on the classpath will be silently ignored. This option can be repeated.
    //      --scan-modules         Scan all resolved modules for test discovery.
    //  -u, --select-uri=URI       Select a URI for test discovery. This option can be repeated.
    //  -f, --select-file=FILE     Select a file for test discovery. This option can be repeated.
    //  -d, --select-directory=DIR Select a directory for test discovery. This option can be repeated.
    //  -o, --select-module=NAME   Select single module for test discovery. This option can be repeated.
    //  -p, --select-package=PKG   Select a package for test discovery. This option can be repeated.
    //  -c, --select-class=CLASS   Select a class for test discovery. This option can be repeated.
    //  -m, --select-method=NAME   Select a method for test discovery. This option can be repeated.
    //  -r, --select-resource=RESOURCE
    //                             Select a classpath resource for test discovery. This option can be repeated.
    //  -i, --select-iteration=TYPE:VALUE[INDEX(..INDEX)?(,INDEX(..INDEX)?)*]
    //                             Select iterations for test discovery (e.g. method:com.acme.Foo#m()[1..2]). This option can be repeated.
    //
    //FILTERS
    //
    //  -n, --include-classname=PATTERN
    //                             Provide a regular expression to include only classes whose fully qualified names match. To avoid loading classes unnecessarily, the default pattern only includes class names that
    //                               begin with "Test" or end with "Test" or "Tests". When this option is repeated, all patterns will be combined using OR semantics. Default: ^(Test.*|.+[.$]Test.*|.*Tests?)$
    //  -N, --exclude-classname=PATTERN
    //                             Provide a regular expression to exclude those classes whose fully qualified names match. When this option is repeated, all patterns will be combined using OR semantics.
    //      --include-package=PKG  Provide a package to be included in the test run. This option can be repeated.
    //      --exclude-package=PKG  Provide a package to be excluded from the test run. This option can be repeated.
    //  -t, --include-tag=TAG      Provide a tag or tag expression to include only tests whose tags match. When this option is repeated, all patterns will be combined using OR semantics.
    //  -T, --exclude-tag=TAG      Provide a tag or tag expression to exclude those tests whose tags match. When this option is repeated, all patterns will be combined using OR semantics.
    //  -e, --include-engine=ID    Provide the ID of an engine to be included in the test run. This option can be repeated.
    //  -E, --exclude-engine=ID    Provide the ID of an engine to be excluded from the test run. This option can be repeated.
    //
    //RUNTIME CONFIGURATION
    //
    //      -cp, --classpath, --class-path=PATH
    //                             Provide additional classpath entries -- for example, for adding engines and their dependencies. This option can be repeated.
    //      --config=KEY=VALUE     Set a configuration parameter for test discovery and execution. This option can be repeated.
    //
}
