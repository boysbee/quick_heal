case class CsmDiscount(jobName:String)

object CsmDiscount {
	import db._

	def findDiscount(conn: Connection) : List[CsmDiscount] =
	using(conn.createStatement) { st=>
		using(st.executeQuery("select * from csm_discount")) { rs =>
			bmap(rs.next){
				new CsmDiscount(rs.getString("job_name"))
			}
		}
	}
}