package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    private TorpedoStore store;

    @BeforeEach
    void setUp() {
        store = new TorpedoStore(3, 0.0);
    }

    @Test
    void initialState() {
        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fireOneTorpedo() {
        assertTrue(store.fire(1));
        assertEquals(2, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fireMultipleTorpedoes() {
        assertTrue(store.fire(2));
        assertEquals(1, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fireAllTorpedoes() {
        assertTrue(store.fire(3));
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void failedFireDoesNotConsumeTorpedoes() {
        store = new TorpedoStore(3, 1.0);
        assertFalse(store.fire(1));
        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fireZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fireNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fireTooManyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> store.fire(4));
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fireFromEmptyStoreThrowsException() {
        store = new TorpedoStore(0, 0.0);
        assertTrue(store.isEmpty());
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
        assertEquals(0, store.getTorpedoCount());
    }
}
