package vn.bkdn.cntt.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Created by XuanVinh on 3/7/2017.
 */
public class CustomPhysicalNaming extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context){
        String customName = name.toString();
        String FistLetterUpperCaseCustomName = customName.substring(0,1).toUpperCase()+customName.substring(1);

        return name.toIdentifier(FistLetterUpperCaseCustomName);
    }
}
