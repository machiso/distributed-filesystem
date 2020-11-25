package org.mac.dfs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class FSDirectory {

    /**
     * 内存中的文件目录树
     */
    private InodeDirectory dirTree;

    public FSDirectory() {
        this.dirTree = new InodeDirectory("/");
    }

    // path = /usr/warehouse/hive
    // 你应该先判断一下，“/”根目录下有没有一个“usr”目录的存在
    // 如果说有的话，那么再判断一下，“/usr”目录下，有没有一个“/warehouse”目录的存在
    // 如果说没有，那么就得先创建一个“/warehosue”对应的目录，挂在“/usr”目录下
    // 接着再对“/hive”这个目录创建一个节点挂载上去
    public void mkdir(String path) {
        synchronized(dirTree) {
            String[] pathes = path.split("/");
            InodeDirectory parent = null;

            for(String splitedPath : pathes) {
                if(splitedPath.trim().equals("")) {
                    continue;
                }

                InodeDirectory dir = findDirectory(dirTree, splitedPath);
                if(dir != null) {
                    parent = dir;
                    continue;
                }

                InodeDirectory child = new InodeDirectory(splitedPath);
                parent.addChild(child);
            }
        }
    }

    /**
     * 对文件目录树递归查找目录
     * @param dir
     * @param path
     * @return
     */
    private InodeDirectory findDirectory(InodeDirectory dir, String path) {
        if(dir.getChildren().size() == 0) {
            return null;
        }

        InodeDirectory resultDir = null;

        for(INode child : dir.getChildren()) {
            if(child instanceof InodeDirectory) {
                InodeDirectory childDir = (InodeDirectory) child;

                if((childDir.getPath().equals(path))) {
                    return childDir;
                }

                resultDir = findDirectory(childDir, path);
                if(resultDir != null) {
                    return resultDir;
                }
            }
        }

        return null;
    }

    interface INode{}

    @Getter
    @Setter
    class InodeDirectory implements INode{
        private String path;
        private List<INode> children;

        public InodeDirectory(String path) {
            this.path = path;
        }

        public void addChild(INode inode) {
            this.children.add(inode);
        }
    }

    @Getter
    @Setter
    class InodeFile implements INode{
        private String name;
    }
}
