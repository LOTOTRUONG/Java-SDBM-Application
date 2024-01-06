module main.sdbm_02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.controlsfx.controls;
    requires java.naming;
    requires lombok;


    opens main.sdbm_02 to javafx.fxml;
    exports main.sdbm_02;
    exports Service;
    opens Service to javafx.fxml;
    exports Metier;
    opens Metier to javafx.base;
}