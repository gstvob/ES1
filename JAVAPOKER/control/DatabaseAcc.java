package control;

import java.sql.*;

public class DatabaseAcc {

	private Connection conn;
	private Statement stmt;
	
	public void connect(String driver, String url, String user, String password) throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);
	}

	public int put(String sqlString) throws Exception {
		try {
			if (this.stmt != null)
				this.stmt.close();
		} catch (Exception ignore) {
		
		}
		this.stmt = this.conn.createStatement();
		return stmt.executeUpdate(sqlString);
	}

	public ResultSet executeSql(String sqlString) throws Exception {
		try {
			if (this.stmt != null)
				this.stmt.close();
		} catch (Exception ignore) {
		
		}
		this.stmt = this.conn.prepareStatement(sqlString);
		return stmt.executeQuery(sqlString);
	}
	
	public boolean check(String user, String pass) throws Exception {
		String sql1 = "SELECT * FROM contas WHERE usuario = ? and senha = ?";
		PreparedStatement st = conn.prepareStatement(sql1);
		st.setString(1, user);
		st.setString(2, pass);
		ResultSet set = st.executeQuery();
		int count = 0;
		while(set.next()) {
			count++;
		}
		if (count == 0) {
			return false;
		} else {
			return true;
		}
		
	}
	public void close() throws Exception {
		try {
			if (this.stmt != null)
				this.stmt.close();
		} catch (Exception ignore) {
		}
		this.conn.close();
	}
}