package util

object DateUtil {
	def str2Date(source : String , pattern : String ) : java.util.Date = {
		if( source == null  || "".equals(source)) {
			return null
		}
		import java.util._
		import java.text._
		var date :java.util.Date = null;
		var currentTime : Calendar = Calendar.getInstance(Locale.US);

		try {
			var fdate : DateFormat = new SimpleDateFormat(pattern);
			fdate.setCalendar(currentTime);
			date = fdate.parse(source);
		}
		catch {
			case e : Throwable =>  None
		}
		return date;
	}

	def date2Str(date : java.util.Date , pattern : String ) : String = {
		if( null == date  ) {
			return ""
		}

		var result : String = null
		try {
			import java.text._
			import java.util._
			var dateFormat : DateFormat = new SimpleDateFormat(pattern,	Locale.US);
			result = dateFormat.format(date);
		}
		catch {
			case e : Throwable =>  None
		}
		return result;
	}
}