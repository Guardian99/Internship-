/**
 * @author Kanishk Rana
 *
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

//This is class template for fast I/O
class Reader 
{
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) 
    {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException
     {
        while ( ! tokenizer.hasMoreTokens() )
         {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException
     {
        return Integer.parseInt( next() );
    }
    
    static double nextDouble() throws IOException
     {
        return Double.parseDouble( next() );
    }
}


class Student
{
  // Data fields are being kept private for access control. Another good Java teaching concept and coding practice.
   private int token;
   private String fname;
   private double cgpa;
   
   // getters being provided for usage in the code. Another good coding practice whose reasoning actually covers important concept of OOP.
   public int getToken() 
   {
      return token;
   }
   public String getFname() 
   {
      return fname;
   }
   public double getCgpa() 
   {
      return cgpa;
   }

   public Student(String fname, double cgpa,int id) 
   {
      
      this.fname = fname;
      this.cgpa = cgpa;
      this.token = id;
   }

   // Setters are not provided so as to not give power to user to change the data. Another example to teach where getters and setters should be provided.
}

// The following class is good example to explain the students how Comparator and Comparable differs and how & when compareTo method is used and its return value
class StudentComparator implements Comparator<Student> 
{
    // The following is just an annotation. Generally considered a good programming practice when building large applications as this will indicate functions that have been over-rided
    @Override 
    public int compare( Student s1, Student s2 ) 
    {
        // The student having the highest Cumulative Grade Point Average (CGPA) is served first.
        // Any students having the same CGPA will be served by name in ascending case-sensitive alphabetical order.
        // Any students having the same CGPA and name will be served in ascending token order.
        
        double s1gpa = s1.getCgpa(); 
        double s2gpa = s2.getCgpa();
        
        if( s1gpa == s2gpa ) 
        {
            
            String name1 = s1.getFname(); 
            String name2 = s2.getFname();

            if( name1.equals( name2 ) ) 
            {
                return s1.getToken() - s2.getToken();
            } 
            else 
            {
                return name1.compareTo( name2 );
            }
        } 
        else 
        {
          // just a fancy way of writing if-else ; also known as ternary operators . Could be used to introduce the students to different style of programming although not used in large scale applications as it might hinder readibility.
            return s1gpa < s2gpa ? 1 : -1; 
        }
        
    }
}

public class Main
{

    public static void main(String[] args) throws IOException
    {
        Reader.init(System.in);
      
        int totalEvents = Reader.nextInt();
        // Creates a PriorityQueue with the specified initial capacity that orders its elements according to the specified comparator.
        PriorityQueue<Student> pq = new PriorityQueue<Student>( totalEvents, new StudentComparator() );

        for(int i=0;i<totalEvents;i++)
        {
            String event = Reader.next();

            if( event.compareTo("SERVED") == 0 ) 
            {
                Student s = pq.poll();
            } 
            else 
            {
                String name=Reader.next();
                Double cgpa=Reader.nextDouble();
                int token=Reader.nextInt();
                pq.add( new Student(name, cgpa,token ) );
                
            }

            
        }
        if( pq.size() != 0 ) 
        {
           while( pq.size() > 0 ) 
            {
                Student s = pq.poll();
                System.out.println( s.getFname() );
            }
           
        } 
        else 
        {
            System.out.println( "EMPTY" );
        }
    }
}