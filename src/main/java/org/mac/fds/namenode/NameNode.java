package org.mac.fds.namenode;

public class NameNode {

    private volatile Boolean shouldRun;

    //负责管理元数据的核心组件
    private FSNamesystem fsNamesystem;

    /**
     * 负责管理集群中所有的Datanode的组件
     */
    private DataNodeManager datanodeManager;

    //NameNode对外提供rpc接口的server，可以响应请求
    private NameNodeRpcServer nameNodeRpcServer;

    public NameNode() {
        this.shouldRun = true;
    }

    public void initialize(){
        this.fsNamesystem = new FSNamesystem();
        this.datanodeManager = new DataNodeManager();
        this.nameNodeRpcServer = new NameNodeRpcServer(this.fsNamesystem,this.datanodeManager);
        this.nameNodeRpcServer.start();
    }

    public static void main(String[] args) {
        NameNode namenode = new NameNode();
        namenode.initialize();
        namenode.run();
    }

    private void run() {
        try {
            while(shouldRun) {
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
