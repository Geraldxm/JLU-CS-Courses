public class BaseDao {
	//ʡ�Ա���������롭��
	private	static String driver;
	private	static String url;
private	static String user;
private	static String password;
Connection conn=null;
	static{
		init();}
	public static void init(){
			Properties params=new Properties();
			String configFile = "database.properties";
			InputStream is=BaseDao.class.getClassLoader()
                                .getResourceAsStream(configFile);
			try {
				params.load(is);
			} catch (IOException e) {//��.}
			driver=params.getProperty("driver");
			url=params.getProperty("url");
			user=params.getProperty("user");
			password=params.getProperty("password");
		}
 //ʡ�������������롭��
}
