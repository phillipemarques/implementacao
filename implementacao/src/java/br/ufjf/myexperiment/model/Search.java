/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.myexperiment.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vitorfs
 */
@XmlRootElement
public class Search {
    private String query;
    private String type;
    private List<Workflow> workflow;
    private List<File> file;
    private List<User> user;
    private List<Pack> pack;
    private List<Group> group;

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    @XmlAttribute
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the workflow
     */
    public List<Workflow> getWorkflow() {
        return workflow;
    }

    /**
     * @param workflow the workflow to set
     */
    @XmlElement
    public void setWorkflow(List<Workflow> workflow) {
        this.workflow = workflow;
    }
    
    /**
     * @return the file
     */
    public List<File> getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    @XmlElement
    public void setFile(List<File> file) {
        this.file = file;
    }

    /**
     * @return the user
     */
    public List<User> getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    @XmlElement
    public void setUser(List<User> user) {
        this.user = user;
    }

    /**
     * @return the pack
     */
    public List<Pack> getPack() {
        return pack;
    }

    /**
     * @param pack the pack to set
     */
    @XmlElement
    public void setPack(List<Pack> pack) {
        this.pack = pack;
    }

    /**
     * @return the group
     */
    public List<Group> getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    @XmlElement
    public void setGroup(List<Group> group) {
        this.group = group;
    }
    

}
