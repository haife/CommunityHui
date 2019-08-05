package com.kaiwukj.android.communityhui.jpush;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Charles
 * @date 2019/8/2.
 * description：Kaiwu
 */
@Entity
public class MessageEntity {
    @Id(autoincrement = true)
    private Long id;
    public String title;
    public String message;
    public long createTime;
    public String Alias;
    @Generated(hash = 1373649859)
    public MessageEntity(Long id, String title, String message, long createTime, String Alias) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.createTime = createTime;
        this.Alias = Alias;
    }
    @Generated(hash = 1797882234)
    public MessageEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public String getAlias() {
        return this.Alias;
    }
    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

  

}
