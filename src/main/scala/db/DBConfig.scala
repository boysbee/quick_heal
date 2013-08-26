package db

case class DBConfig(url : String , user : String , pass : String)

object DBConfig {
	val url = "jdbc:oracle:thin:@172.16.49.14:1521:TEST01"
	// val url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"
	val user = "amdapp22"
	val pass = "amdapp22"
	
	def apply = new DBConfig(url,user,pass)
}