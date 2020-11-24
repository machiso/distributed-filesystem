package org.mac.dfs;

public class NameNode {

    private volatile Boolean shouldRun;

    //负责管理元数据的核心组件
    private FSNamesystem fsNamesystem;

    //NameNode对外提供rpc接口的server，可以响应请求
    private NameNodeRpcServer nameNodeRpcServer;

    public NameNode() {
        this.shouldRun = true;
    }

    public void initialize(){
        this.fsNamesystem = new FSNamesystem();
        this.nameNodeRpcServer = new NameNodeRpcServer(this.fsNamesystem);
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
