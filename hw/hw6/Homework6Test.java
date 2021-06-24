/* Homework6Test.java */

import dict.*;
import linkedList.*;

/**
 *  Initializes a hash table, then stocks it with random SimpleBoards.
 **/

public class Homework6Test {

  /**
   *  Generates a random 8 x 8 SimpleBoard.
   **/

  private static SimpleBoard randomBoard() {
    SimpleBoard board = new SimpleBoard();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
	double fval = Math.random() * 12;
	int value = (int) fval;
	board.setElementAt(x, y, value);
      }
    }
    return board;
  }

  /**
   *  Empties the given table, then inserts "numBoards" boards into the table.
   *  @param table is the hash table to be initialized.
   *  @param numBoards is the number of random boards to place in the table.
   **/

  public static void initTable(HashTableChained table, int numBoards) {
    table.makeEmpty();
    for (int i = 0; i < numBoards; i++) {
      table.insert(randomBoard(), i);
    }
  }

  /**
   *  Creates a hash table and stores a certain number of SimpleBoards in it.
   *  The number is 100 by default, but you can specify any number at the
   *  command line.  For example:
   *
   *    java Homework6Test 12000
   **/

  public static void main(String[] args) {
    int numBoards;

    if (args.length == 0) {
      numBoards = 100;
    } else {
      numBoards = Integer.parseInt(args[0]);
    }
    
    HashTableChained table = new HashTableChained(numBoards);
    
//    System.out.println("=====================size, isEmpty=========================");
//    System.out.println("table's size is: " + table.size());
//    System.out.println("table is Empty: " + table.isEmpty());
//
//    System.out.println("=====================insert================================");
//    table.insert("1", "The first one");
//    table.insert("2", "The second one");
//    table.insert("3", "The third one");
//    table.insert("what", "nani?");
//    table.insert("the","Eh-heng");
//    table.insert("hell!","impolite");
//    System.out.println("table's size is: " + table.size());
//    System.out.println("table is Empty: " + table.isEmpty());
//    System.out.println(table);
//    
//    System.out.println("====================find, remove===========================");
//    Entry e1 = table.find("6");
//    if(e1 != null) {
//    	 System.out.println("The item found is: [ " + e1.toString() + " ]");
//    } else {
//    	System.out.println("The is no such item in the table to be found.");
//    }
//    
//
//    Entry e2 = table.remove("hell!");
//    if(e2 != null) {
//    	System.out.println("The item deleted is: [ " + e2.toString() + " ]");
//    } else {
//    	System.out.println("The is no such item in the table to be deleted.");
//    }
//    
//    System.out.println("=====================makeEmpty=============================");
//    table.makeEmpty();
//    System.out.println(table);
    
    
    initTable(table, numBoards);

    // To test your hash function, add a method to your HashTableChained class
    // that counts the number of collisions--or better yet, also prints
    // a histograph of the number of entries in each bucket.  Call this method
    // from here.
    
    System.out.println("The number of input:" + table.size());
    System.out.println("The number of buckets:" + table.buckets.length);
    System.out.println(table);
    int co = table.countCollision();
    double expectedCo =  table.size() - table.buckets.length+ table.buckets.length*Math.pow(1-1.0/table.buckets.length,table.size());
    System.out.println("Collision: "+ co);
    System.out.println("Expected collision: "+ expectedCo);
  
  }

}
