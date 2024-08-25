module web.hello {
    requires spring.boot.starter.web;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires org.apache.tomcat.embed.core;

    opens web.hello to spring.core;
    exports web.hello;
}