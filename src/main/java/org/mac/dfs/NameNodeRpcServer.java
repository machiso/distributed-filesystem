package org.mac.dfs;


public class NameNodeRpcServer {

    private FSNamesystem fsNamesystem;

    public NameNodeRpcServer(FSNamesystem fsNamesystem) {
        this.fsNamesystem = fsNamesystem;
    }

    public Boolean mkdir(String path){
        return fsNamesystem.mkdir(path);
    }

    public void start() {
        System.out.println("开始监听指定的rpc server的端口号，来接收请求");
    }
}
