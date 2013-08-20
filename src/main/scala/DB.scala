package db


object DB {
	def apply(url: String , user : String , pass : String ) : DB = {
		new DB(url,user,pass)
	}
}

class DB(url:String , user : String , pass : String) {
	var driver = "oracle.jdbc.OracleDriver"
	var dbUrl : String = url
	var dbUser : String  = user
	var dbPassword : String = pass

	def connect() : java.sql.Connection = {
		var connection : java.sql.Connection = null
		try {
			println("@@ driver -> %s ".format(driver))
			Class.forName(driver)
			println("@@ url : %s user : %s , pass : %s ".format(dbUrl,dbUser,dbPassword))
			connection = java.sql.DriverManager.getConnection(dbUrl, dbUser, dbPassword)

		} catch {
			case e => e.printStackTrace
		}
		return connection
	}
	def query(sql:String) : java.sql.ResultSet = {

		var connection : java.sql.Connection = null
		var result :  java.sql.ResultSet = null
		try {
			connection = connect()
			val stmt:java.sql.Statement = connection.createStatement()
			println("@@ query with sql ->  %s".format(sql))
			result = stmt.executeQuery(sql);

		} catch {
			case e => e.printStackTrace
		}
		finally {
			if( null != connection ) {
				try {
					connection.close()
				}
				catch {
					case e => None
				}
				
			}
		}
		return result
	}

	def execute(sql:String) : scala.Boolean = {
		var connection : java.sql.Connection = null
		var result  = false
		try {
			connection = connect()
			val stmt:java.sql.Statement = connection.createStatement()
			println("@@ query with sql ->  %s".format(sql))
			var returnCount = stmt.executeUpdate(sql);
			if( returnCount > 0) {
				result = true
			}
			else 
			{
				result = false
			}
		} 
		catch {
			case e => e.printStackTrace
		}
		finally {
			if( null != connection ) {
				try {
					connection.close()
				}
				catch {
					case e => None
				}
				
			}
		}
		return result	
	}
}