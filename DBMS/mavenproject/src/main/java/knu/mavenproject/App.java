package knu.mavenproject;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import spark.Spark;

/**
 * Hello world!
 *
 */
//get
//put
public class App 
{
    public static void main( String[] args )
    {        
        //Spark.get("/hello", (req, res) -> "Hello World!!!!!!");
    	
    	// TODO code application logic here 
    	try { 
    	Class.forName("org.postgresql.Driver"); 
    	System.out.println("Postgresql JDBC Driver loaded"); 
    	} catch (ClassNotFoundException e) { 
    	System.out.println("Driver failed"); 
    	e.printStackTrace(); 
    	return; 
    	} 
    	try { 
    	try (Connection connection = DriverManager.getConnection( 
    	"jdbc:postgresql://127.0.0.1:5432/db1", 
    	"postgres", "1")) { 
    	System.out.println("good connection"); 
    	exequteQuery(connection); 
    	exequteQuery2(connection,"IT");
    	exequteQuery2(connection,"Medicine");
    	} 

    	} catch (SQLException e) { 
    	System.out.println("Connection Failed!"); 
    	e.printStackTrace(); 
    	} 
    	
    	
   	
    }


    //SELECT * FROM framework INNER JOIN project ON framework_name=?
    //SELECT framework_name FROM framework LEFT JOIN project ON framework_name=?


    
    
static String start = "<html>"+
"<head>"+
"<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
"</head>"+
""+
"<body>"+
"<button>JetJSON</button>"+
"<table>"+
"<tr></tr>"+
""+
"</table>"+
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>"+
"<script>"+
"var i=0,k;"+
"$(\"button\").click(function()  {"+
"	$.getJSON(\"http://localhost:4567/hello1\",function(obj) {"+
"		$.each(obj,function(key,value) {		"+
"		$(\"tr\").append(\"<td>\"+value[0]+\"</td>\"+\"<td>\"+value[1]+\"</td>\"+\"<td>\"+value[2]+\"</td>\"+\"<td>\"+value[3]+\"</td>\");			"+
"		});"+
"	});"+
"});"+
"</script>"+
""+
"<div></div>"+
"</body>"+
"</html>";
	



static String startIt = "<html>"+
"<head>"+
"<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
"</head>"+
""+
"<body>"+
"<button>JetJSON</button>"+
"<table>"+
"<tr></tr>"+
""+
"</table>"+
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>"+
"<script>"+
"var i=0,k;"+
"$(\"button\").click(function()  {"+
"	$.getJSON(\"http://localhost:4567/hello2\",function(obj) {"+
"		$.each(obj,function(key,value) {		"+
"		$(\"tr\").append(\"<td>\"+value[0]+\"</td>\"+\"<td>\"+value[1]+\"</td>\"+\"<td>\"+value[2]+\"</td>\"+\"<td>\"+value[3]+\"</td>\");			"+
"		});"+
"	});"+
"});"+
"</script>"+
""+
"<div></div>"+
"</body>"+
"</html>";

static String startMedicine = "<html>"+
"<head>"+
"<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
"</head>"+
""+
"<body>"+
"<button>JetJSON</button>"+
"<table>"+
"<tr></tr>"+
""+
"</table>"+
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>"+
"<script>"+
"var i=0,k;"+
"$(\"button\").click(function()  {"+
"	$.getJSON(\"http://localhost:4567/hello3\",function(obj) {"+
"		$.each(obj,function(key,value) {		"+
"		$(\"tr\").append(\"<td>\"+value[0]+\"</td>\"+\"<td>\"+value[1]+\"</td>\"+\"<td>\"+value[2]+\"</td>\"+\"<td>\"+value[3]+\"</td>\");			"+
"		});"+
"	});"+
"});"+
"</script>"+
""+
"<div></div>"+
"</body>"+
"</html>";

static String post = "<html>"+
"<head>"+
"<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
"</head>"+
""+
"<body>"+
"<button>PostJSON</button>"+
"<table>"+
"<tr></tr>"+
""+
"</table>"+
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>"+
"<script>"+
"var i=0,k;"+
"$(\"button\").click(function()  {"+
"	$.post(\"http://localhost:4567/hello1\",function(obj) {"+
"		$.each(obj,function(key,value) {		"+
"		$(\"tr\").append(\"<td>\"+value[0]+\"</td>\"+\"<td>\"+value[1]+\"</td>\"+\"<td>\"+value[2]+\"</td>\"+\"<td>\"+value[3]+\"</td>\");			"+
"		});"+
"	},\"json\");"+
"});"+
"</script>"+
""+
"<div></div>"+
"</body>"+
"</html>";
	



		public static boolean exequteQuery(Connection c) { 
		try { 
		try (Statement s = c.createStatement()) {  
		ResultSet rs = s.executeQuery("SELECT * FROM framework INNER JOIN project ON true"); 
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		int k=0;
		while (rs.next()) { 
		for (int i = 1; i <= 4; i++) { 
		String abr = rs.getString(i); 
		System.out.println(abr);
		list.add(abr);
		}
		k++;
		obj.put(k, list);
		list = new JSONArray();
		} 
		try {

    		FileWriter file = new FileWriter("c:\\test.json");
    		file.write(obj.toJSONString());
    		Spark.get("/hello1", (req, res) -> obj.toJSONString());
    		Spark.get("/start", (req, res) -> {return start;});  
    		Spark.post("/hello1", (req,res) -> obj.toJSONString());
    		Spark.post("/start2", (req,res) -> {return post;});
    		
    		file.flush();
    		file.close();

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		return true; 
		} 
		} catch (SQLException e) { 
		System.out.println("eroor"); 
		e.printStackTrace(); 
		return false; 
		} 
		}
	
    public static boolean exequteQuery2(Connection c, String p){
            final String query = "SELECT * FROM framework INNER JOIN project ON framework_name=?";

            try{
            try(PreparedStatement s = c.prepareStatement(query)){
                s.setString(1, p); 
                int i=0;
                int k=0;
                JSONObject obj = new JSONObject();
                JSONArray list = new JSONArray();
                ResultSet rs = s.executeQuery();
                while(rs.next()){
                	for(i=1;i<5;i++){
                    String adr = rs.getString(i);
                    System.out.println(adr);
                    list.add(adr);                   
                  }
                	k++;
            		obj.put(k, list);
            		list = new JSONArray();
                }
                
                try {

            		FileWriter file = new FileWriter("c:\\test.json");
            		file.write(obj.toJSONString());            		
            		if(p=="IT")
            		{
            		Spark.get("/hello2", (req, res) -> obj.toJSONString());
            		Spark.get("/start/"+p, (req, res) -> {return startIt;});}
            		else{
            			Spark.get("/hello3", (req, res) -> obj.toJSONString());
            			Spark.get("/start/"+p, (req, res) -> {return startMedicine;});}
            		           		
            		file.flush();
            		file.close();

            	} catch (IOException e) {
            		e.printStackTrace();
            	}
                return true;
            }
            }catch(SQLException e){
                System.out.println("quey execution Failed!Check output console");
                e.printStackTrace();
                return false;
            }
            
           
        }
    	
    	

    	
}
