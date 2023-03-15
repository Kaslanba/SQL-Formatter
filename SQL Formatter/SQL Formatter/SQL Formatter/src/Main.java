import java.util.Scanner;

public class Main {


    public static void main(String[] args){

        System.out.println("Please enter the messy SQL code");


        Scanner scanner=new Scanner(System.in);

        String st="";

        while (!st.contains("##")){
            st += " "+scanner.nextLine();
        }



        FormatPart formatter=new FormatPart();
        System.out.println("******************** \n");
        st = formatter.format(st.replace("##", " "));
        st = formatter.formatParenthesis(st);

        System.out.println(st);
        System.out.println("\n********************");
        System.out.println("\n SQL Code has formatted");


    }
}

    /*
        SELECT supplier_name, city  FROM suppliers  INNER      JOIN
        contacts WHERE supplier_id > 500 and x=2 or (m >=8) ORDER BY supplier_name ASC,
            city DESC;SELECT supplier_name, PARIS  fROM suppliers NATURAL LEFT OUTER JOIN contacts WHERE supplier_id <> 500 and x = 2
         ##

        */
/*
update a set a.ID = b.ID, Name = b.Name from Table1
inner join (select ID, Name from Table2 where Active <> 0 and Current <> 0)  b
on a.ownerid = b.ownerid and a.type = b.type and b.version = '87.1'
and a.Name is Null where a.expired <> 0
##
*/

/*
update a set a.ID = b.ID, Name = b.Name from Table1
inner join (select ID, Name from Table2 where (Active <> 0) and Current <> 0)  b
on a.ownerid = b.ownerid and a.type = b.type and b.version = '87.1'
and a.Name is Null where a.expired <> 0 ##  */