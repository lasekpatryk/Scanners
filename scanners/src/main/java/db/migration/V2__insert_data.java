package db.migration;


import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class V2__insert_data extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        for (int i = 1; i <= 90; i++) {
            new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                    .execute("INSERT INTO SCANNER(ID ,IN_SERVICE ,LOGGED_USER ,SCANNER_NUMBER ,TIME_OF_LOGIN) VALUES ("+ i +", FALSE, '-', "+ i +", '2022-05-09 07:00:00')");
        }
    }
}
