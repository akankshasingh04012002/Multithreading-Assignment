import java.util.concurrent.locks.{ReadWriteLock, ReentrantReadWriteLock}

class WriterPreferenceReadWriteLock[A] {
  private val readWriteLock: ReadWriteLock = new ReentrantReadWriteLock()
  private var list: List[A] = List.empty

  def read(): List[A] = {
    val lock = readWriteLock.readLock()
    lock.lock()
    try {
      list
    } catch {
      case ex: Exception => println(s"Exception occurred during read operation: ${ex.getMessage}")
        List.empty
    } finally {
      lock.unlock()
    }
  }

  def write(value: A): Unit = {
    val lock = readWriteLock.writeLock()
    lock.lock()
    try {
      list = value :: list
    } catch {
      case ex: Exception => println(s"Exception occurred during write operation: ${ex.getMessage}")
    } finally {
      lock.unlock()
    }
  }

  def setResources(value: List[A]): Unit = {
    val lock = readWriteLock.writeLock()
    lock.lock()
    try {
      list = value
    } catch {
      case ex: Exception => println(s"Exception occurred during setResources operation: ${ex.getMessage}")
    } finally {
      lock.unlock()
    }
  }
}

