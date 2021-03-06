package net.sf.odinms.server;

import net.sf.odinms.client.MapleClient;
import net.sf.odinms.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AutoRegister {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MapleClient.class);
    //private static final int ACCOUNTS_PER_IP = 5;
    public static final boolean autoRegister = true;
    public static boolean success;

    public static boolean getAccountExists(String login) {
        boolean accountExists = false;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name FROM accounts WHERE name = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                accountExists = true;
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            log.warn("XSource: Error acquiring the account of (" + login + ") check AutoRegister.");
        }
        return accountExists;
    }

    public static void createAccount(String login, String pwd, String eip) {
        try {
            PreparedStatement ipq = DatabaseConnection.getConnection().prepareStatement("SELECT lastknownip FROM accounts WHERE lastknownip = ?");
            ipq.setString(1, eip.substring(1, eip.lastIndexOf(':')));
            ResultSet rs = ipq.executeQuery();
            if (!rs.first() || rs.last() && rs.getRow() < 5) {
                try {
                    PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO accounts (name, password, email, birthday, macs, lastknownip) VALUES (?, ?, ?, ?, ?, ?)");
                    ps.setString(1, login);
                    ps.setString(2, pwd);
                    ps.setString(3, "no@email.provided");
                    ps.setString(4, "1990-01-01");
                    ps.setString(5, "00-00-00-00-00-00");
                    ps.setString(6, eip.substring(1, eip.lastIndexOf(':')));
                    ps.executeUpdate();
                    ps.close();
                    success = true;
                } catch (Exception ex) {
                    log.warn("XSource: Error creating the account of (" + login + " | " + pwd + " | " + eip + ").");
                    ipq.close();
                    rs.close();
                    return;
                }
            }
            ipq.close();
            rs.close();
        } catch (Exception ex) {
            log.warn("XSource: Error in 'createAccount' check AutoRegister.");
        }
    }
}