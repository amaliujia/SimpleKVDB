package amaliujia.cv.SimpleKVDB;

import lombok.Getter;
import lombok.Setter;

public class DBOptions {
  @Setter
  @Getter
  private long maxDataFileSize = 1024 * 1024 * 1024; // 1GB maximum file size.
}
