// package com.example.minishop;

// import java.sql.Types;

// import javax.sql.DataSource;

// import org.apache.commons.dbcp.BasicDataSource;
// import org.hibernate.MappingException;
// import org.hibernate.dialect.Dialect;
// import org.hibernate.dialect.function.StandardSQLFunction;
// import org.hibernate.dialect.function.SQLFunctionTemplate;
// import org.hibernate.dialect.function.VarArgsSQLFunction;
// import org.hibernate.dialect.identity.IdentityColumnSupport;
// import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
// import org.hibernate.type.StringType;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.core.env.Environment;
// import org.springframework.jdbc.datasource.DriverManagerDataSource;

// public class SQLDialect extends Dialect {

//     // @Autowired
//     // Environment env;

//     public SQLDialect() {
//         init();
//     }

    

//     // @Bean
//     // public DataSource dataSource() {
//     //     final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//     //     dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//     //     dataSource.setUrl(env.getProperty("spring.datasource.url"));
//     //     // dataSource.setUsername(env.getProperty("user"));
//     //     // dataSource.setPassword(env.getProperty("password"));
//     //     return dataSource;
//     // }

//     public void init() {
//         registerColumnType(Types.BIT, "integer");
//         registerColumnType(Types.TINYINT, "tinyint");
//         registerColumnType(Types.SMALLINT, "smallint");
//         registerColumnType(Types.INTEGER, "integer");
//         registerColumnType(Types.BIGINT, "bigint");
//         registerColumnType(Types.FLOAT, "float");
//         registerColumnType(Types.REAL, "real");
//         registerColumnType(Types.DOUBLE, "double");
//         registerColumnType(Types.NUMERIC, "numeric");
//         registerColumnType(Types.DECIMAL, "decimal");
//         registerColumnType(Types.CHAR, "char");
//         registerColumnType(Types.VARCHAR, "varchar");
//         registerColumnType(Types.LONGVARCHAR, "longvarchar");
//         registerColumnType(Types.DATE, "date");
//         registerColumnType(Types.TIME, "time");
//         registerColumnType(Types.TIMESTAMP, "timestamp");
//         registerColumnType(Types.BINARY, "blob");
//         registerColumnType(Types.VARBINARY, "blob");
//         registerColumnType(Types.LONGVARBINARY, "blob");
//         // registerColumnType(Types.NULL, "null");
//         registerColumnType(Types.BLOB, "blob");
//         registerColumnType(Types.CLOB, "clob");
//         registerColumnType(Types.BOOLEAN, "integer");

//         registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
//         registerFunction("mod", new SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"));
//         registerFunction("substr", new StandardSQLFunction("substr", StringType.INSTANCE));
//         registerFunction("substring", new StandardSQLFunction("substr", StringType.INSTANCE));
//     }

//     // @Override
//     // public IdentityColumnSupport getIdentityColumnSupport() {
//     //     return new SQLiteIdentityColumnSupport();
//     // }

//     // @Override
//     public boolean hasAlterTable() {
//         return false;
//     }

//     // @Override
//     public boolean dropConstraints() {
//         return false;
//     }

//     // @Override
//     public String getDropForeignKeyString() {
//         return "";
//     }

//     // @Override
//     public String getAddForeignKeyConstraintString(String cn, String[] fk, String t, String[] pk, boolean rpk) {
//         return "";
//     }

//     // @Override
//     public String getAddPrimaryKeyConstraintString(String constraintName) {
//         return "";
//     }

//     public boolean supportsIdentityColumns() {
//         return true;
//     }

//     public boolean hasDataTypeInIdentityColumn() {
//         return false; // As specify in NHibernate dialect
//     }

//     public String getIdentityColumnString() {
//         // return "integer primary key autoincrement";
//         return "integer";
//     }

//     public String getIdentitySelectString() {
//         return "select last_insert_rowid()";
//     }

//     public boolean supportsLimit() {
//         return true;
//     }

//     protected String getLimitString(String query, boolean hasOffset) {
//         return new StringBuffer(query.length() + 20).append(query).append(hasOffset ? " limit ? offset ?" : " limit ?")
//                 .toString();
//     }

//     public boolean supportsTemporaryTables() {
//         return true;
//     }

//     public String getCreateTemporaryTableString() {
//         return "create temporary table if not exists";
//     }

//     public boolean dropTemporaryTableAfterUse() {
//         return false;
//     }

//     public boolean supportsCurrentTimestampSelection() {
//         return true;
//     }

//     public boolean isCurrentTimestampSelectStringCallable() {
//         return false;
//     }

//     public String getCurrentTimestampSelectString() {
//         return "select current_timestamp";
//     }

//     public boolean supportsUnionAll() {
//         return true;
//     }

//     public String getAddColumnString() {
//         return "add column";
//     }

//     public String getForUpdateString() {
//         return "";
//     }

//     public boolean supportsOuterJoinForUpdate() {
//         return false;
//     }

//     public boolean supportsIfExistsBeforeTableName() {
//         return true;
//     }

//     public boolean supportsCascadeDelete() {
//         return false;
//     }
// }

// class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

//     @Override
//     public boolean supportsIdentityColumns() {
//         return true;
//     }

//     @Override
//     public String getIdentitySelectString(String table, String column, int type) throws MappingException {
//         return "select last_insert_rowid()";
//     }

//     @Override
//     public String getIdentityColumnString(int type) throws MappingException {
//         return "integer";
//     }
// }