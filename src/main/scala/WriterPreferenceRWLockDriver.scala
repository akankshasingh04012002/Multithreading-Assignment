object WriterPreferenceRWLockDriver extends App {

  private val readWriteLock = new WriterPreferenceReadWriteLock[Int]

  private val writerThreadOne = new Thread(() => {
    readWriteLock.setResources(List(5, 6))
    println("Written 5 and 6 to the list")
  })
  writerThreadOne.start()

  private val writerThreadTwo = new Thread(() => {
    readWriteLock.write(4)
    println("Written 4 to the list")
    Thread.sleep(500)
    readWriteLock.write(3)
    println("Written 3 to the list")
  })
  writerThreadTwo.start()

  private val readerThreadOne = new Thread(() => {
    readWriteLock.read()
    println("Resources read by ReaderThreadOne")
  })
  readerThreadOne.start()

  private val readerThreadTwo = new Thread(() => {
    readWriteLock.read()
    println("Resources read by ReaderThreadTwo ")
  })
  readerThreadTwo.start()

  writerThreadOne.join()
  writerThreadTwo.join()
  readerThreadOne.join()
  readerThreadTwo.join()
}
