package ru.iaulitin.delayed.queue.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.iaulitin.delayed.queue.core.supportive.DummyClass1;
import ru.iaulitin.delayed.queue.core.supportive.DummyClass1QueueManager;
import ru.iaulitin.delayed.queue.core.supportive.DummyClass2;
import ru.iaulitin.delayed.queue.core.supportive.DummyClass2QueueManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QueueManagerTest {


    @Autowired
    private DummyClass1QueueManager dummyClass1QueueManager;

    @Autowired
    private DummyClass2QueueManager dummyClass2QueueManager;


    /**
     * Тест проверяет, что для единственного объекта в очереди задача будет выполнена по прошествии таймаута.
     *
     * @throws Exception в случае непредвиденного исключения во время выполнения теста.
     */
    @Test
    void test010_whenOneObjectInQueue_thenDeactivated() throws Exception {
        DummyClass1 dummyObject1 = new DummyClass1();
        dummyObject1.setActive(true);
        dummyObject1.setDelayMillis(100L);

        dummyClass1QueueManager.handle(dummyObject1);
        assertTrue(dummyObject1.isActive());

        Thread.sleep(150);
        assertFalse(dummyObject1.isActive());
    }


    /**
     * Тест проверяет, что для двух объектов одного типа в очереди задачи будет выполнена по прошествии таймаутов,
     * в нужном порядке.
     *
     * @throws Exception в случае непредвиденного исключения во время выполнения теста.
     */
    @Test
    void test011_whenTwoObjectsInQueue_thenDeactivated() throws Exception {
        DummyClass1 dummyObject1 = new DummyClass1();
        dummyObject1.setActive(true);
        dummyObject1.setDelayMillis(100L);
        dummyClass1QueueManager.handle(dummyObject1);

        DummyClass1 dummyObject2 = new DummyClass1();
        dummyObject2.setActive(true);
        dummyObject2.setDelayMillis(200L);
        dummyClass1QueueManager.handle(dummyObject2);

        assertTrue(dummyObject1.isActive());
        assertTrue(dummyObject2.isActive());

        Thread.sleep(150);
        assertFalse(dummyObject1.isActive());
        assertTrue(dummyObject2.isActive());

        Thread.sleep(250);
        assertFalse(dummyObject1.isActive());
        assertFalse(dummyObject2.isActive());
    }


    /**
     * Тест проверяет, что для двух объектов разных типов в очереди задачи будет выполнена по прошествии таймаутов,
     * в нужном порядке.
     *
     * @throws Exception в случае непредвиденного исключения во время выполнения теста.
     */
    @Test
    void test012_whenTwoObjectsOfDifferentTypesInQueue_thenDeactivated() throws Exception {
        DummyClass1 dummyObject1 = new DummyClass1();
        dummyObject1.setActive(true);
        dummyObject1.setDelayMillis(100L);
        dummyClass1QueueManager.handle(dummyObject1);

        DummyClass2 dummyObject2 = new DummyClass2();
        dummyObject2.setActive(true);
        dummyObject2.setDelayMillis(200L);
        dummyClass2QueueManager.handle(dummyObject2);

        assertTrue(dummyObject1.isActive());
        assertTrue(dummyObject2.isActive());

        Thread.sleep(150);
        assertFalse(dummyObject1.isActive());
        assertTrue(dummyObject2.isActive());

        Thread.sleep(100);
        assertFalse(dummyObject1.isActive());
        assertFalse(dummyObject2.isActive());
    }


    /**
     * Тест проверяет, что при добавлении объекта в очередь на первое место
     * и при наличии свободных обработчиков задачи будут выполнены.
     *
     * @throws Exception в случае непредвиденного исключения во время выполнения теста.
     */
    @Test
    void test013_whenObjectAtFirstPlaceAndHaveFreeWorkers_thenDeactivated() throws Exception {
        DummyClass1 dummyObject1 = new DummyClass1();
        dummyObject1.setActive(true);
        dummyObject1.setDelayMillis(300L);
        dummyClass1QueueManager.handle(dummyObject1);

        DummyClass1 dummyObject2 = new DummyClass1();
        dummyObject2.setActive(true);
        dummyObject2.setDelayMillis(100L);
        dummyClass1QueueManager.handle(dummyObject2);

        assertTrue(dummyObject1.isActive());
        assertTrue(dummyObject2.isActive());

        Thread.sleep(150);
        assertTrue(dummyObject1.isActive());
        assertFalse(dummyObject2.isActive());

        Thread.sleep(200);
        assertFalse(dummyObject1.isActive());
        assertFalse(dummyObject2.isActive());
    }

    //todo добавить тест, когда не будет свободных воркеров
}
