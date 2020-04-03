# Multithreading app

## Task
Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров. Число
контейнеров, находящихся в текущий момент в порту и на корабле, должно
быть неотрицательным и превышающим заданную грузоподъемность суд-
на и вместимость порта. В порту работает несколько причалов. У одного
причала может стоять один корабль. Корабль может загружаться у причала,
разгружаться или выполнять оба действия.

## Introduction
Архитектура проекта представляет следующее: в пакете doc у нас хранятся
классы, которые представляют наши сущности (Doc, Ship, LoadingShip,
UnloadingShip). В пакете mvc хранятся классы, представляющие логику
реализации паттерна MVC. Класс Ship - абстрактный и реализует интерфейс
Runnable. Классы LoadingShip и UnloadingShip расширяют класс Ship и
переопределяют метод run(). Класс Doc представляет порт и содержит методы
getContainer(int amount, int place) и putContainer(int amount, int place)
для загрузки и разгрузки контейнеров в порт соответственно. Причалы
реализованны статическим, булевским массивом PORTS в классе Doc (значение
true означает, что место занято, false - свободно). Класс Controller имеет
метод startProgramm(), в котором создаются 4 экземпляра класса LoadingShip
и UnloadingShip.

## Version 1 (Synchronized blocks)
В первой версии методы getContainer(int amount, int place) и
putContainer(int amount, int place) были помечены идентификатором
synchronized. В методе run() сначала проверяется наличие свободного места.
Если оно есть вызывается метод getContainer() или putContainer(), если его
нет, то поток уходит в ожидание методом wait(). После идет освобождение
занятого места методом releasePlace(int place).

## Version 2 (Synchronizers)
Во второй версии используется синхронизатор Semaphor на 3 разрешения
(количество причалов). В методе run() сначала вызывается метод acquire(),
если разрешения есть, то для корабля вызывается метод getContainer() или
putContainer(), если нет - то поток блокируется. В конце метода run()
вызывается метод release(), который освобождает разрешение семафора.

## Version 3 (Executor(ThreadPool) with stream)
В третьей версии в класс Model был добавлен ExecutorService executor =
Executors.newFixedThreadPool(3). В классе Controller мы получаем ссылку на
executor и с помощью метода submit() добавляем задачу в очередь на
выполнение для executor. Для показа реализации стримов был создан класс
Helper в пакете helper, в котором мы выбираем корабли, которые имеют
грузоподъемность больше 10 и являються экземплярами класса LoadingShip, а
потом выводим их номер на экран.

## Version 4 (RWLock with Conditions)
В четвертой версии методы getContainer(int amount, int place) и
putContainer(int amount, int place) уже не являються synchronized. Вместо
этого мы создаем Lock readLock = readWriteLock.readLock(),
Lock writeLock = readWriteLock.writeLock(), а также
Condition emptyCondition = writeLock.newCondition() и
Condition fullCondition = writeLock.newCondition(). Программа напоминает
первую версию за исключением того, что вместо wait() и notifyAll() мы
используем await() и signalAll(), а вместо synchronized блоков мы
захватываем мониторы методом lock() и освобождаем их методом unlock() в
блоке finally.