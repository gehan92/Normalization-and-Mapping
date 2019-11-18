/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remapping;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gehan Ishanka
 */
public class MainClass 
{
                private static String TableName;
                private static String val1;
                private static String val2;
                private static String val3;
                private static String val4;
                private static String val5;
                private static String val6;
    public static Connection getConnection() throws Exception 
            
    {
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/dbms";
                String username = "root";
                String password = "";
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(url, username, password);
                return conn;
    }
    
    public static void main(String[] args) throws IOException 
    {
        
        File file = new File("E:\\rescerch\\Remapping\\" + "DisplayMapping.txt"); //input location research
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully now you can start"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
        
         ArrayList size = new ArrayList();
               for(int bq=1; bq<8; bq++)
               {
                   size.add(bq);
               
               }
               
//               

                Scanner scanner = new Scanner(System.in);
                BufferedReader br = null;
                String line;

                try {
                    br = new BufferedReader(new FileReader("E:\\rescerch\\Remapping\\" + "1to1_relationship.txt" /*scanner.next()*/));

                } catch (FileNotFoundException fnfex) {

                    System.out.println(fnfex.getMessage() + "not found");
                    System.exit(0);
                }

                 ArrayList Relationship = new ArrayList();
                 ArrayList tableValues = new ArrayList();
                 ArrayList primarykey = new ArrayList();
                 ArrayList tablename = new ArrayList();
                 ArrayList tablename1 = new ArrayList();
                 ArrayList attribute = new ArrayList();
                 ArrayList mvattribute = new ArrayList();
                 ArrayList pvattribute1 = new ArrayList();
                 ArrayList pvattribute = new ArrayList();
                 ArrayList attribute2 = new ArrayList();
                 ArrayList attribute3 = new ArrayList();
                 ArrayList attribute23 = new ArrayList();
                 ArrayList attribute1 = new ArrayList();
                 ArrayList Alltablename = new ArrayList();
                 ArrayList finalSizeData = new ArrayList();
                 ArrayList FinalAlltableColums = new ArrayList();
                 
                  while ((line = br.readLine()) != null) {
                    tableValues.add(line);  //inputData.txt  
                }

                br.close();

                ArrayList temp = new ArrayList();
                ArrayList Allentityname = new ArrayList();
                ArrayList mainDataset = new ArrayList();
                System.out.println("tablevalue " +tableValues);
                
                for (int i = 0; i < tableValues.size(); i++) 
                {
                        
                    System.out.println("---->"+tableValues.get(i));
                    temp.add(tableValues.get(i));
                   // System.out.println("=====iiiiiiiii===="+temp);
                    
                    for (Object rawRow : temp) 
                     {
                       // System.out.println("******************"+temp);
                            String rawRowSt = (String) rawRow;
                            String[] m = rawRowSt.split(",");
                            for (int r = 0; m.length > r; r++) 
                            {
                               //System.out.println("+++++++++++++++++++++++++++++"+m[r]);
                                            if(m[r].indexOf("[[PK]")== -1 && m[r].indexOf("[PK]")== -1 && m[r].indexOf(" [PK]")== -1 && m[r].indexOf("[MV]")== -1 && m[r].indexOf(".")== -1 && m[r].indexOf("R_")== -1  )
                                            {
                                            attribute23.add(m[r]);
                                            //System.out.println("(((((((((((((((------------)))))))))) "+attribute23);
                                            }
                                            if(m[r].indexOf("R_")== -1 )
                                            {}
                                            else
                                            {
                                                Relationship.add(m[r]);
                                                System.out.println("RelationShip==="+Relationship);
                                            }
                                            if(m[r].indexOf(".")== -1 )
                                            {
                                               //System.out.println("-----tabl|||||ename-------->"+m[r]); 
                                               attribute.add(m[r]);
                                               String b[]=new String[10];
                                               b[0]= (String) attribute.get(0);
                                               // System.out.println("////////////"+b[0]);
                                               String str1 = b[0];
                                                    
                                                int x = str1.indexOf("[");
                                                str1 = str1.substring(0, x) + str1.substring(x + 1); 

                                                b[0]=null;
                                                b[0]=str1;
                                                int y = str1.indexOf("[");
                                                str1 = str1.substring(0, y) + str1.substring(y + 1); 


                                                b[0]=null;
                                                b[0]=str1;
                                                int Z = str1.indexOf("P");
                                                str1 = str1.substring(0, Z) + str1.substring(Z + 1); 

                                                b[0]=null;
                                                b[0]=str1;
                                                int S = str1.indexOf("K");
                                                str1 = str1.substring(0, S) + str1.substring(S + 1); 

                                                b[0]=null;
                                                b[0]=str1;
                                                int T = str1.indexOf("]");
                                                str1 = str1.substring(0, T) + str1.substring(T + 1); 

                                                attribute2.add(str1);
                                               //System.out.println("------attribute----->"+attribute2);       
                                                      // System.out.println("============"+str1);
                                                str1=null;
                                                       //System.out.println("============"+str1);
                                                      // attribute.clear();
                                           }
                                            else{
                                                primarykey.add(m[r]);
                                                String a[]=new String[10];
                                                a[0]= (String) primarykey.get(0);
                                                String str = a[0]; 
                                                int x = str.indexOf("]");
                                                int y = str.indexOf(".");
                                                str = str.substring(0, x) + str.substring(x + 1); 
                                                str = str.substring(0, y) + str.substring(y + 1); 
                                                tablename1.add(str);
                                               // System.out.println("----tableName---->"+tablename1);
                                                str=null;
                                                //System.out.println(" )))))))))))))))))))-->"+primarykey);     
                                                primarykey.clear();
                                                }
                               
                                         
                                            if(m[r].indexOf("[MV]")== -1 )
                                            {
                                            }
                                            else
                                            {   
                                                attribute1.add(m[r]);
                                               // System.out.println("---multival------------------>"+attribute1);
                                                String bb[]=new String[1];
                                                bb[0]= (String) attribute1.get(0);
                                                String str2 = bb[0];
                                                int xx = str2.indexOf("[");
                                                str2 = str2.substring(0, xx) + str2.substring(xx + 1); 
                                                  
                                                bb[0]=null;
                                                bb[0]=str2;
                                                int ZZ = str2.indexOf("M");
                                                str2 = str2.substring(0, ZZ) + str2.substring(ZZ + 1); 
                                                
                                                bb[0]=null;
                                                bb[0]=str2;
                                                int SS = str2.indexOf("V");
                                                str2 = str2.substring(0, SS) + str2.substring(SS + 1); 

                                                bb[0]=null;
                                                bb[0]=str2;
                                                int TT = str2.indexOf("]");
                                                str2 = str2.substring(0, TT) + str2.substring(TT + 1); 
                                                
                                                mvattribute.add(str2);
                                              //  System.out.println("-----------[MV]1------------> "+mvattribute);
                                                str2=null;
                                            }
                                         
                                            if(m[r].indexOf("[PK]")== -1 )
                                            {
                                            }
                                            else
                                            { 
                                                System.out.println("-------pk-------------->"+m[r]);
                                                
                                                attribute3.add(m[r]);
                                                System.out.println("-------pk2------------->"+attribute3);
                                               
                                                String bbb[]=new String[1];
                                                bbb[0]= (String) attribute3.get(0);
                                                String str3 = bbb[0];
                                                System.out.println("================================================="+str3);
                                                 
                                                int xxx = str3.indexOf("[");
                                                str3 = str3.substring(0, xxx) + str3.substring(xxx + 1); 
                                                  
                                                bbb[0]=null;
                                                bbb[0]=str3;
                                                int ZZZ = str3.indexOf("P");
                                                str3 = str3.substring(0, ZZZ) + str3.substring(ZZZ + 1); 
                                                    
                                                bbb[0]=null;
                                                bbb[0]=str3;
                                                int SSS = str3.indexOf("K");
                                                str3 = str3.substring(0, SSS) + str3.substring(SSS + 1); 

                                                bbb[0]=null;
                                                bbb[0]=str3;
                                                int TTT = str3.indexOf("]");
                                                str3 = str3.substring(0, TTT) + str3.substring(TTT + 1); 
                                                
                                                pvattribute1.add(str3);
                                                //System.out.println("-------pk3-------------->"+pvattribute1);
                                                str3=null;
                                            }

                            }
                    System.out.println("Tablename===>"+tablename1);
                    System.out.println("Attributesub1======>"+attribute23);
                    System.out.println("Multivalue=[MV]==>"+mvattribute);
                    System.out.println("PvData=[PK]==>"+attribute3);
                    
                        for (int j = 0; j < Relationship.size(); j++) {
                              mainDataset.add(Relationship.get(j));
                         }
                    
                         for (int j = 0; j < tablename1.size(); j++) {
                            // String temp2 = (String) attribute3.get(j);
                            // temp2 = temp2.replaceAll("[^a-zA-Z0-9 -]", "");
                              mainDataset.add(tablename1.get(j));
                         }
                         for (int j = 0; j < attribute3.size(); j++) {
                             String temp2 = (String) attribute3.get(j);
                             temp2 = temp2.replaceAll("[^a-zA-Z0-9 -]", "");
                             //replaseData1.add(temp2)
                             mainDataset.add(temp2);
                         }
                         for (int j = 0; j < attribute23.size(); j++) {
                             
                             mainDataset.add(attribute23.get(j));
                         }
                         for (int j = 0; j < mvattribute.size(); j++) {
                               
                              mainDataset.add(mvattribute.get(j));
                         }
                   
                   
                    System.out.println("----final------------->"+mainDataset);
                    finalSizeData.add(mainDataset.size());
                    FinalAlltableColums.addAll(mainDataset);
                    
                                    try
                                      {
                                      FileWriter fstream = new FileWriter("DisplayMapping.txt",true);
                                      BufferedWriter out = new BufferedWriter(fstream); 
                                      out.write(""+mainDataset.get(0));
                                       out.newLine();
                                      
                                          for (int j = 1; j < mainDataset.size(); j++) {
                                               out.write(""+mainDataset.get(j)+"  |  ");
                                                
                                          }
                                               out.write("\n\n");
                                               out.newLine();
                                               out.close();
                                              
                                      
                                      } catch (Exception e)
                                      {
                                        System.err.println("Error: " + e.getMessage());
                                      }    
                    
                   
                    
                    
                    
                  //  System.out.println("Attrybute_set1====>"+attribute23);
                    
                    Alltablename.addAll(tablename1);
                     attribute2.clear();
                     mvattribute.clear();
                     pvattribute.clear();
                     pvattribute1.clear();
                     attribute23.clear();
                     tablename1.clear();
                     attribute1.clear();
                     attribute3.clear();
                     mainDataset.clear();
                     Relationship.clear();
                     }
                    
                     System.out.println("888888888888"+Alltablename);
                     System.out.println(" ");
                     System.out.println(" ");
                     temp.clear();
                }
                
                
                                 
                // System.out.println("----final--+++++++----------->"+mainDataset);
                 System.out.println("--------------------size------>"+finalSizeData);
                 System.out.println("------------------------------> "+FinalAlltableColums);
             //  test(emp);
                System.out.println("Alltablename  >>> "+FinalAlltableColums.size());
                //Integer bn = null;
                
                ArrayList DataTemp = new ArrayList();
                Integer aa = null;
                 int q =0;
                 int w =0;
     
                 for (int t = 0; t < finalSizeData.size(); t++) 
                 { 
                          aa=0;
                          aa = (int) finalSizeData.get(t);
                          q=aa+q;//q=6
                          w=q-aa;
                                        for (int j = w; j < q; j++) 
                                        {
                                          DataTemp.add(FinalAlltableColums.get(j));
                                            // System.out.println("----"+DataTemp);
                                        }
                         System.out.println("++++++++++++++"+DataTemp);
                         if(DataTemp.size()==3){
                            final StringBuffer s1 =new StringBuffer();
                            s1.append("create table "+DataTemp.get(0)+" (" +DataTemp.get(1)+ " VARCHAR(20), " +DataTemp.get(2)+ " VARCHAR(20)) "); 
                            final  String emp1 = s1.toString();
                            test(emp1);
                         } 
                         if(DataTemp.size()==4){
                            final StringBuffer s2 =new StringBuffer();
                            s2.append("create table "+DataTemp.get(0)+" (" +DataTemp.get(1)+ " VARCHAR(20), " +DataTemp.get(2)+ " VARCHAR(20), " +DataTemp.get(3)+ " VARCHAR(20)) "); 
                            final  String emp2 = s2.toString();
                            test(emp2);
                         } 
                         if(DataTemp.size()==5){
                            final StringBuffer s5 =new StringBuffer();
                            s5.append("create table "+DataTemp.get(0)+" (" +DataTemp.get(1)+ " VARCHAR(20), " +DataTemp.get(2)+ " VARCHAR(20), " +DataTemp.get(3)+ " VARCHAR(20), " +DataTemp.get(4)+ " VARCHAR(20)) "); 
                            final  String emp5 = s5.toString();
                            test(emp5);
                         } 
                         if(DataTemp.size()==6){
                            final StringBuffer s3 =new StringBuffer();
                            s3.append("create table "+DataTemp.get(0)+" (" +DataTemp.get(1)+ " VARCHAR(20), " +DataTemp.get(2)+ " VARCHAR(20), " +DataTemp.get(3)+ " VARCHAR(20), " +DataTemp.get(4)+ " VARCHAR(20), " +DataTemp.get(5)+ " VARCHAR(20)) "); 
                            final  String emp3 = s3.toString();
                            test(emp3);
                         } 
                          DataTemp.clear(); 
                         
                }
    }
    
    
    
    
    
    
    
    
    
                 public static void test(final String emp)
                        {
                            Connection conn = null;
                            Statement stmt = null;

                            try {
                                    conn = getConnection();
                                    stmt = conn.createStatement();
                                    stmt.executeUpdate(emp);
                                  //  stmt.executeUpdate("insert into gehan(id, firstName, lastName, title, salary) values(100, 'Damindu', 'thewarapperuma', 'dccn', 24000)");
                                   // stmt.executeUpdate("insert into gehan(id, firstName, lastName, title, salary) values(200, 'gsp', 'thewarapperuma', 'dccn', 34000)");
                                    System.out.println("CreateEmployeeTableMySQL: main(): table created.");
                                  } catch (ClassNotFoundException e) {
                                    System.out.println("error: failed to load MySQL driver.");
                                    e.printStackTrace();
                                  } catch (SQLException e) {
                                    System.out.println("error: failed to create a connection object.");
                                    e.printStackTrace();
                                  } catch (Exception e) {
                                    System.out.println("other error:");
                                    e.printStackTrace();
                                  } finally {
                                            try {
                                              stmt.close();
                                              conn.close();        
                                            } catch (SQLException e) {
                                              e.printStackTrace();
                                            }
                                            }
                        }


}

