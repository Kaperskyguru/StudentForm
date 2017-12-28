package studentform;

import java.sql.*;

public class DB {

    Connection con;
    Statement st;
    ResultSet rs;

    public DB() {

        Runtime run = Runtime.getRuntime();
        try {
            run.exec("taskmgr");
        } catch (Exception e) {

        }

//    int[] chars = createArray();
//    System.out.println("The lowercase letters are:");
//    displayArray(chars);
//    
//    int[] counts = countLetters(chars);
//    
//    System.out.println();
//    System.out.println("The occurences of the numbers are:");
//    displayCounts(counts);
//}
// public static int[] createArray(){
//     
//     int[] chars = new int[100];
//     
//    for(int i = 0; i < chars.length; i++){
//        chars[i] = (int)( Math.random() * (100));
//    } 
//    return chars;
// }
// 
// public static void displayArray(int[] chars){
//     for (int i = 0; i < chars.length; i++){
//         if((i + 1) % 20 == 0){
//             System.out.println(chars[i]);
//         }
//         
//         else
//             System.out.print(chars[i] + " ");
// }
//     
// }
// 
// 
// public static int[] countLetters(int[] chars){
//     int[] counts  = new int[5];
//     for(int i = 0; i <chars.length; i++){
//         //counts[chars[i] - 1]++;
//         if (chars[i] >= 1 && chars[i] <= 20)
//             counts[0]++;
//         else if (chars[i] >= 21 && chars[i] <= 40)
//             counts[1]++;
//         else if (chars[i] >= 41 && chars[i] <= 60)
//             counts[2]++;
//         else if (chars[i] >= 61 && chars[i] <= 80)
//             counts[3]++;  
//         else if (chars[i] >= 81 && chars[i] <= 100)
//             counts[4]++;
//         //else 
//             //counts[5]++;
//     }
//     
//     return counts;
// }
// 
// public static void displayCounts(int[] counts){
//     int b[] = {0,1,2,3,4};
//  for(int i = 0; i < counts.length; i++){
//      if(b[i] == 0)
//         System.out.println(counts[i] + " of "  + "(0 - 20) "); 
//       else if(b[i] == 1)
//         System.out.println(counts[i] + " of "  + "(21 - 40) "); 
//       else if(b[i] == 2)
//         System.out.println(counts[i] + " of "  + "(41 - 60) "); 
//       else if(b[i] == 3)
//         System.out.println(counts[i] + " of "  + "(61 - 80) "); 
//       else if(b[i] == 4)
//         System.out.println(counts[i] + " of "  + "(80 - 100) "); 
//  }   
    }

    public static void main(String[] arg) {
        new DB();
    }

}
