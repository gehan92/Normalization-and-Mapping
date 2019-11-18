/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remapping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gehan Ishanka
 */
public class Remapping {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = null;
        String line;
        
        File file = new File("E:\\rescerch\\Remapping\\" + "1to1_relationship.txt"); //input location
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully now you can start"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
        try {
            br = new BufferedReader(new FileReader("E:\\rescerch\\Remapping\\" + "mapping.txt" /*scanner.next()*/));
        } catch (FileNotFoundException fnfex) {
            System.out.println(fnfex.getMessage() + "not found");
            System.exit(0);
        }
        ArrayList tableValues = new ArrayList();
        ArrayList multivalueData2nf = new ArrayList();
        ArrayList repeatDataset = new ArrayList();
        while ((line = br.readLine()) != null) {
            tableValues.add(line);  //inputData.txt  
        }
        br.close();
        System.out.println("input data "+tableValues);
            int a =0;
            ArrayList primarykey = new ArrayList();
            ArrayList tablename = new ArrayList();
            ArrayList attribute = new ArrayList();
            ArrayList cardinality = new ArrayList();
            ArrayList Relationship = new ArrayList();
            ArrayList newtable = new ArrayList();
            ArrayList Allentityname = new ArrayList();
            ArrayList Allentityname1 = new ArrayList();
            ArrayList tableset = new ArrayList();
            ArrayList size = new ArrayList();
            ArrayList ConnectedTable = new ArrayList();
            ArrayList tryremoveduplicate = new ArrayList(); 
            ArrayList finalone2 = new ArrayList(); 
             
            for (Object rawRow : tableValues) 
            {
              String rawRowSt = (String) rawRow;
              String[] m = rawRowSt.split(";");
                for (int i = 0; m.length > i; i++) 
                    {
                        if(m[i].indexOf(".") == 0 )
                        {
                        Allentityname.add(m[i]);
                        }
                    }
                    Set<Integer> primesWithoutDuplicates = new LinkedHashSet<Integer>(Allentityname);
                    Allentityname.clear();
                    Allentityname.addAll(primesWithoutDuplicates);
            }
                    a=Allentityname.size()+1;
                    //System.out.println("aaaaaaaaaaa"+a);
            for (Object rawRow : tableValues) 
            {
                  String rawRowSt = (String) rawRow;
                  String[] v = rawRowSt.split(";");
                  for (int i = 0; v.length > i; i++)
                  {
                        if(v[i].indexOf("[PK]") == 0){
                        primarykey.add(v[i]);
                        }
                        
                        else if(v[i].indexOf(".") == 0 || v[i].indexOf("*") ==0 )
                        {
                        tablename.add(v[i]);

                        }
                        else if(v[i].indexOf("@") == 0)
                        {
                        cardinality.add(v[i]);
                        }
                        else if(v[i].indexOf("R_") == 0)
                        {
                        Relationship. add(v[i]);
                        }
                        else
                        {
                        attribute.add(v[i]);
                        }
                  } 
                              System.out.println("primarykey== "+primarykey);
                              System.out.println("tablesname=="+tablename);
//                                System.out.println("attribute=="+attribute);
//                                System.out.println("relationship=="+Relationship);
//                                System.out.println("cardinality=="+cardinality);
//                                System.out.println("  ");
                            for (int i = 0;  tablename.size()>i; i++) 
                            {
                                newtable.add(tablename.get(i));
                            }
                            for (int i = 0;  cardinality.size()>i; i++) 
                            {
                                newtable.add(cardinality.get(i));
                            }
                            for (int i = 0;  Relationship.size()>i; i++) 
                            {
                                newtable.add(Relationship.get(i));
                            }
                            for (int i = 0;  primarykey.size()>i; i++) 
                            {
                                newtable.add(primarykey.get(i));
                            }

                            for (int i = 0;  attribute.size()>i; i++) 
                            {
                                newtable.add(attribute.get(i));
                            }
                           // System.out.println("size"+newtable.size());
                            size.add(newtable.size());
                           // System.out.println("table "+newtable);
                            tableset.addAll(newtable);
                            //System.out.println(" ");
                            primarykey.clear();
                            tablename.clear();
                            attribute.clear();
                            Relationship.clear();
                            cardinality.clear();
                            newtable.clear();
                } 
     ArrayList tableset22 = new ArrayList();
     ArrayList tableset222 = new ArrayList();
     ArrayList PriviousTable222 = new ArrayList();
     ArrayList supertable = new ArrayList();             
     ArrayList tableset2 = new ArrayList();
     ArrayList Cardinalityset = new ArrayList();
     ArrayList TableOriginal = new ArrayList();
     ArrayList TableOriginal1 = new ArrayList();
     ArrayList TableOriginal11 = new ArrayList();
     ArrayList finalone = new ArrayList();
     ArrayList singalfinal = new ArrayList();
     Map keytable = new HashMap();
     String maintablenamecopy = null; 
     Integer bn = null;
     
     for (int i = 0; Allentityname.size() >i; i++)  // [.Bank, .Branch, .User, .employee]
         {    
             System.out.println("main conected table >>>>"+Allentityname.get(i)+"______**");
             maintablenamecopy=(String) Allentityname.get(i);      //.Bank
              int q =0;
              int w =0;
              for (int n = 0; n < size.size(); n++)       //size.size()       //[[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ], [.employee, *Bank, @ 1, @ m, [PK]empid, empname, district, emptp, empemail]]
                {
                bn = (int) size.get(n);
                q=bn+q;
                w=q-bn;
                for (int j = w; j <q; j++) 
                    {
                        tableset22.add(tableset.get(j));     //[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ]
                    }
                    if(tableset22.get(0).equals(Allentityname.get(i)))   //[.Bank,.Bank]
                        {        
                          ConnectedTable.add(tableset22.get(1)); //[*Branch]
                        }
                        tableset22.clear();                         //[]
                }   
                    //start mapping  --------------------------------------------------------------- 
                   //[*Branch,*User]
                     Integer bn1 = null;   
                     for (int c = 0; ConnectedTable.size() >c; c++) //:ex[.Branch]
                     {   
                        // System.out.println(">>>>222"+ConnectedTable.get(c));
                            int q1 =0;
                            int w1 =0;
                        for (int nm = 0; nm < size.size(); nm++)            //size.size()       //[[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ], [.employee, *Bank, @ 1, @ m, [PK]empid, empname, district, emptp, empemail]]
                        {
                           bn1 = (int) size.get(nm);
                            q1=bn1+q1;
                            w1=q1-bn1;
                            for (int j = w1; j <q1; j++)
                            {
                                tableset222.add(tableset.get(j));       //[.Branch;@1;@m;R_has;.Bank;[PK]branchid;branchname;district;BRtp;BRemail]
                            } 
                            if((tableset222.get(0).equals(ConnectedTable.get(c))) && (Allentityname.get(i).equals(tableset222.get(1))))    //[.Branch, .Bank, @1, @1, R_has, [PK]branchid, branchname, district, BRtp, BRemail]
                            {                          
                                String cal1;
                                String cal2;
                                cal1 = (String) tableset222.get(2); //@1
                                cal2 =  (String) tableset222.get(3);//@1
                                if(cal1.equals("@n" ) && cal2.equals("@m" )) //true  
                                {   
                                    ArrayList tableset22check1 = new ArrayList(); 
                                    String maintablenamecopy1 = null; 
                                    Integer bn2 = null;
                                         for (int ii = 0; i >= ii; ii++) //[.Bank]
                                         {  
                                          int q2 =0;
                                          int w2 =0;
                                          for (int nn = 0; nn < size.size(); nn++)            //size.size()       //[[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ], [.employee, *Bank, @ 1, @ m, [PK]empid, empname, district, emptp, empemail]]
                                            {
                                             bn2 = (int) size.get(nn);            
                                             q2=bn2+q2;
                                             w2=q2-bn2;
                                               for (int jj = w2; jj <q2; jj++) 
                                                 {
                                                  tableset22check1.add(tableset.get(jj)); //[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ]
                                                 }    
                                                   String data1 =null;
                                                   String data2 =null;
                                                   data1 =(String) tableset22check1.get(2);
                                                   data2 =(String) tableset22check1.get(3); 
                                                   if(ConnectedTable.get(i).equals(tableset22check1.get(0)))
                                                   {
                                                    TableOriginal.add(tableset22check1.get(5));
                                                   }
                                                   if(Allentityname.get(i).equals(tableset22check1.get(0))&&cal1.equals("@n" ) && cal2.equals("@m" ) ) //.Bank
                                                    {
                                                        TableOriginal.add(tableset22check1.get(4));
                                                        TableOriginal.add(tableset22check1.get(5));
                                                              if(TableOriginal.size()>3)
                                                              {
                                                                Set<Integer> primesWithoutDuplicates2 = new LinkedHashSet<Integer>(TableOriginal);
                                                                TableOriginal.clear();
                                                                TableOriginal.addAll(primesWithoutDuplicates2); 
                                                                finalone2.addAll(TableOriginal);
                                                              }
                                                                //System.out.println(" ");
                                                                TableOriginal.clear();
                                                                data1="";
                                                                data2="";
                                                                }
                                                                tableset22check1.clear();
                                            }
                                         }
                                }
                                if(cal1.equals("@1" ) && cal2.equals("@1" )) //true  
                                {  
                                         ArrayList tableset22check18 = new ArrayList(); 
                                         String maintablenamecopy18 = null; 
                                         Integer bn28 = null;
                                         for (int ii8 = 0; i >= ii8; ii8++) //[.Bank]
                                         {  
                                          int q28 =0;
                                          int w28 =0;
                                          for (int nn8 = 0; nn8 < size.size(); nn8++)            //size.size()       //[[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ], [.employee, *Bank, @ 1, @ m, [PK]empid, empname, district, emptp, empemail]]
                                            {
                                             bn28 = (int) size.get(nn8);            
                                             q28=bn28+q28;
                                             w28=q28-bn28;
                                               for (int jj8 = w28; jj8 <q28; jj8++) 
                                                 {
                                                  tableset22check18.add(tableset.get(jj8)); //[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ]
                                                 }                                                                                                          
                                                   String data18 =null;
                                                   String data28 =null;
                                                   data18 =(String) tableset22check18.get(2);
                                                   data28 =(String) tableset22check18.get(3);       
                                                   if((Allentityname.get(i).equals(tableset22check18.get(0)))&& data18.equals("@1" ) && data28.equals("@1" ) ) //.Bank
                                                    {     
                                                     for (int c123 = 5; c123 < tableset22check18.size(); c123++) 
                                                       {
                                                        TableOriginal.add(tableset22check18.get(c123)); //[.Bank details]
                                                       }
                                                                tryremoveduplicate.addAll(TableOriginal);
                                                                finalone.addAll(TableOriginal);
                                                                TableOriginal.clear();
                                                                data18="";
                                                                data28="";
                                                    }
                                                                tableset22check18.clear();
                                            }
                                        }
                                }
                                else if(cal1.equals("@m" )&& cal2.equals("@1" ))
                                {       
                                         //System.out.println("*******"+tableset222);
                                         ArrayList tableset22check11 = new ArrayList(); 
                                         String maintablenamecopy11 = null; 
                                         Integer bn3 = null;
                                         for (int iii = 0; i>= iii; iii++) //[.Bank, .Branch, .User, .employee]
                                         {  
                                            int q3 =0;
                                            int w3 =0;
                                             for (int nnn = 0; nnn < size.size(); nnn++)            //size.size()       //[[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ], [.employee, *Bank, @ 1, @ m, [PK]empid, empname, district, emptp, empemail]]
                                                {
                                                     bn3 = (int) size.get(nnn);
                                                     q3=bn3+q3;
                                                     w3=q3-bn3;
                                                     for (int jjj = w3; jjj <q3; jjj++) 
                                                     {    
                                                      tableset22check11.add(tableset.get(jjj)); //[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ]
                                                     }   
                                                     String data11 =null;
                                                     String data22 =null;
                                                     data11 =(String) tableset22check11.get(2);
                                                     data22 =(String) tableset22check11.get(3);
                                                     if((Allentityname.get(i).equals(tableset22check11.get(0)))&& (data22.equals("@m" )&& data11.equals("@1" ))) //.Bank
                                                     {                    
                                                      TableOriginal1.add(tableset222.get(5));
                                                      System.out.println();
                                                      System.out.println("final table "+TableOriginal1);
                                                      finalone.addAll(TableOriginal1);
                                                      TableOriginal1.clear();
                                                      data11 ="";
                                                      data22 ="";
                                                      }       
                                                tableset22check11.clear();
                                               }  
                                        }   
                                }
                                cal1="";
                                cal2="";
                          }
                            tableset222.clear();
                         }
                    }
                      try
                           {
                           FileWriter fstream = new FileWriter("1to1_relationship.txt",true);
                           BufferedWriter out = new BufferedWriter(fstream);
                     
                        System.out.println("map data entity set11 = "+ConnectedTable); 
                        ConnectedTable.clear();
                        maintablenamecopy = "";
                        
                        finalone.add(Allentityname.get(i));
                        Set<Integer> primesWithoutDuplicates22 = new LinkedHashSet<Integer>(finalone);
                        finalone.clear();
                        finalone.addAll(primesWithoutDuplicates22);
                        if(finalone.get(0).equals(Allentityname.get(i)))
                        {  
                            ArrayList tableset22check114 = new ArrayList(); 
                            ArrayList maintablenamecopy114 = new ArrayList(); 
                            Integer bn34 = null;
                                            int q34 =0;
                                            int w34 =0;
                                             for (int nnn4 = 0; nnn4 < size.size(); nnn4++)            //size.size()       //[[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ], [.employee, *Bank, @ 1, @ m, [PK]empid, empname, district, emptp, empemail]]
                                                {   
                                                     bn34 = (int) size.get(nnn4);
                                                     q34=bn34+q34;
                                                     w34=q34-bn34;
                                                     for (int jjj4 = w34; jjj4 <q34; jjj4++) 
                                                     {    
                                                      tableset22check114.add(tableset.get(jjj4)); //[.Bank, *Branch, @ 1, @ 1, [PK]bankid, bankname, [MV]tp, email ]
                                                     }   
                                                    if(finalone.get(0).equals(tableset22check114.get(0))){
                                                        for (int jy = 5; jy < tableset22check114.size(); jy++) {
                                                            maintablenamecopy114.add(tableset22check114.get(jy));
                                                        }
                                                        singalfinal.addAll(maintablenamecopy114);
                                                    }
                                                        tableset22check114.clear();
                                             }
                                                        maintablenamecopy114.clear();
                            Set<Integer> primesWithoutDuplicates223 = new LinkedHashSet<Integer>(singalfinal);
                            singalfinal.clear();
                            singalfinal.addAll(primesWithoutDuplicates223); 
                            singalfinal.add(finalone.get(0));
                            System.out.println("Table " +singalfinal);
                             out.write(""+singalfinal);
                             out.newLine();
                        }
                        else
                       {
                        System.out.println("Table " +finalone);
                         out.write(""+finalone);
                         out.newLine();
                       }
                        singalfinal.clear();
                        finalone.clear();
                        Set<Integer> primesWithoutDuplicates222 = new LinkedHashSet<Integer>(finalone2);
                        finalone2.clear();
                        finalone2.addAll(primesWithoutDuplicates222);
          
                        if(finalone2.isEmpty()){
                       // System.out.println("good++++++++++++++++++++=============>"+finalone2);
                        }
                        else{
                        System.out.println("Table" +finalone2);
                        out.write(""+finalone2);
                         out.newLine();
                        }
                           out.close();            
                           } catch (Exception e)
                             {
                                System.err.println("Error: " + e.getMessage());
                             }
                        finalone2.clear();
         }
       }
                    
     }
    
   
    
    
    
    
    

                  
              
              
              
               




          
          
          
         
              
              
                      
            
               
               
   
       
                 
            
         
    
                
    
         
         
         
         
         
    

    
