package org.mac.fds.namenode;

/**
 * 负责管理元数据的核心组件
 */
public class FSNamesystem {

    private FSDirectory fsDirectory;
    private FSEditlog fsEditlog;

    public FSNamesystem() {
        this.fsDirectory = new FSDirectory();
        this.fsEditlog = new FSEditlog();
    }

    public Boolean mkdir(String path) {
        fsDirectory.mkdir(path);
        fsEditlog.logEdit("创建了一个目录:"+path);
        return true;
    }
}
