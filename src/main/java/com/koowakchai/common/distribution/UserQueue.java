package com.koowakchai.common.distribution;

import com.koowakchai.hibernate.entity.TUserEntity;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class UserQueue {
    private static UserQueue instance = null;
    private static BlockingQueue<TUserEntity> queue = null;

    private UserQueue(){
        queue =  new LinkedBlockingDeque<TUserEntity>();
    }

    public static UserQueue getInstance(){
        if(instance == null){
            instance = new UserQueue();
        }
        return instance;
    }

    /**
     * 取下一个员工
     * @return
     */
    public TUserEntity takeNext(){
        TUserEntity user = null;
        if (queue.size() == 0){
            return null;
        }
        try {
            user = queue.take();
            this.put(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 将新员工放入队列中未尾
     */
    public void put(TUserEntity user){
        if(queue.contains(user)){
            return;
        }else{
            try {
                queue.put(user);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void putAll(Collection<TUserEntity> users){
        if (users == null){
            queue =  new LinkedBlockingDeque<TUserEntity>();
        }
        else{
            for(TUserEntity user : users){
                this.put(user);
            }
        }
    }

    /**
     * 将已不存在的员工移除
     */
    public void remove(TUserEntity user){
        if(queue.contains(user)){
            queue.remove(user);
        }
    }

    /**
     * 获取目前队列中所有的user
     * @return
     */
    public Object[] getAllUsers(){
        Object[] obj = queue.toArray();
        return obj;
    }


}
