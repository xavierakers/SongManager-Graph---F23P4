import student.TestCase;

public class HashTableTest extends TestCase {
    public void testInsertAndSeach1() {
        Hash table = new Hash(4);
        Record record1 = new Record("record1", null);
        assertTrue(table.insert(record1));
        assertEquals(table.search(record1.getKey()), record1);

    }


    /**
     * Test HashTable insert with keys in and out of range
     * 1. Insert Record1 with large key
     * 2. Insert Record2 with small key
     * 3. Search Record1
     * 4. Search Record2 -- Fail
     */
    public void testInsertDuplicateAndSearch() {
        Hash table = new Hash(8);
        Record record1 = new Record("record1", null);
        Record record2 = new Record("record1", null);

        assertTrue(table.insert(record1));
        assertFalse(table.insert(record2));
        assertEquals(table.search(record1.getKey()), record1);
        assertNotSame(table.search(record2.getKey()), record2);

    }
    /**
     * Search empty hash table
     */
    public void testNullSeach() {
        Hash table = new Hash(4);
        assertNull(table.search("1"));
    }
    
    /**
     * Basic test for deletions
     * 1. Insert Records
     * 2. Delete Records
     * 3. Search Records
     */
    public void testDelete() {
        Hash table = new Hash(256);
        Record record1 = new Record("record1", null);
        Record record2 = new Record("record2", null);
        Record record3 = new Record("record3", null);

        assertTrue(table.insert(record1));
        assertTrue(table.insert(record2));
        assertTrue(table.insert(record3));

        assertEquals(table.delete(record2.getKey()), record2);
        assertEquals(table.delete(record1.getKey()), record1);
        assertEquals(table.search(record2.getKey()), null);
        assertNull(table.search(record2.getKey()));
        assertNull(table.search(record1.getKey()));
    }
    
    /**
     * Test Deleting and Inserting New Records
     * 1. Insert Records
     * 2. Delete Records
     * 3. Search Records
     * 
     * Requiring double hashing and probing
     */
//    public void testDeleteInsert() {
//        Hash table = new Hash(256);
//        Record record1 = new Record("record1", null);
//        Record record2 = new Record("record1", null);
//        Record record3 = new Record("record123", null);
//        Record record4 = new Record("recor3456781", null);
//        Record record5 = new Record("record123456", null);
//
//        assertTrue(table.insert(record1));
//        assertTrue(table.insert(record2));
//        assertTrue(table.insert(record3));
//
//        assertEquals(table.delete(record3.getKey()), record3);
//        assertNull(table.search(record3.getKey()));
//
//        assertTrue(table.insert(record4));
//        assertEquals(record4, table.search(record4.getKey()));
//
//        assertEquals(table.delete(record2.getKey()), record2);
//        assertNull(table.search(record2.getKey()));
//
//        assertTrue(table.insert(record5));
//        assertEquals(record5, table.search(record5.getKey()));
//    }
    
}
