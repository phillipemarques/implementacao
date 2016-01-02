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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author vitorfs
 * @author lesimoes
 * @author phillipe
 */

@XmlRootElement(name = "SearchResult")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchResult")

public abstract class SearchResult {
    
    //workflow
    @XmlElement(name = "id", required = true)
    private Integer id;
    @XmlAttribute(name = "resource")
    private String resource;
    @XmlElement(name = "preview")
    private String preview;    
    @XmlElement(name = "thumbnail")
    private String thumbnail;    
    @XmlElement(name = "thumbnail-big")
    private String thumbnailBig; 
    @XmlElement(name = "svg")
    private String svg;       
    @XmlElement(name = "license-type")
    private String licenseType;
    @XmlElement(name = "content-uri")
    private String contentUri; 
    @XmlElement(name = "content-type")
    private String contentType;     
    @XmlElement(name = "content")
    private String content;     
    
    @XmlElementWrapper(name="tags")
    @XmlElement(name="tag")
    private List<Tag> tags;
    
    @XmlElementWrapper(name="taggings")
    @XmlElement(name="tagging")
    private List<Tagging> taggings;

    @XmlElementWrapper(name="versions")
    @XmlElement(name="workflow")
    private List<Workflow> versions;
    
    @XmlElementWrapper(name="reviews")
    @XmlElement(name="review")
    private List<Review> reviews;
    
    @XmlElementWrapper(name="comments")
    @XmlElement(name="comment")
    private List<Comment> comments;
    
    @XmlAttribute(name = "uri")
    private String uri;
    @XmlElement(name = "description", required = true)
    private String description;
    @XmlElement(name = "title", required = true)
    private String title;    
    @XmlElement(name = "type", required = true)
    private String type;    
    @XmlElement(name = "created-at", required = true)
    private String createdAt;
    @XmlElement(name = "updated-at", required = true)
    private String updatedAt;
    @XmlElement(name = "statistics", required = true)
    private Statistics total;
    @XmlElement(name = "uploader", required = true)
    private String uploader;    

    //file
    @XmlElement(name="filename", required = true)
    private String filename;

    //user
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "email")
    private String email;
    
    @XmlElement(name = "avatar")
    private String avatar;
    
    @XmlElement(name = "city")
    private String city;  
    
    @XmlElement(name = "country")
    private String country;  
    
    
    
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }    
    
    public String getResource() {
        return resource;
    }
 
    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }    
    
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }
    
    public String getCreatedAt(){
        return createdAt;
    }
  
    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }
    
    public String getUpdatedAt(){
        return updatedAt;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total.getTotal();
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total.setTotal(total); 
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the uploader
     */
    public String getUploader() {
        return uploader;
    }

    /**
     * @param uploader the uploader to set
     */
    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    /**
     * @return the preview
     */
    public String getPreview() {
        return preview;
    }

    /**
     * @param preview the preview to set
     */
    public void setPreview(String preview) {
        this.preview = preview;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return the svg
     */
    public String getSvg() {
        return svg;
    }

    /**
     * @param svg the svg to set
     */
    public void setSvg(String svg) {
        this.svg = svg;
    }

    /**
     * @return the thumbnailBig
     */
    public String getThumbnailBig() {
        return thumbnailBig;
    }

    /**
     * @param thumbnailBig the thumbnailBig to set
     */
    public void setThumbnailBig(String thumbnailBig) {
        this.thumbnailBig = thumbnailBig;
    }

    /**
     * @return the licenseType
     */
    public String getLicenseType() {
        return licenseType;
    }

    /**
     * @param licenseType the licenseType to set
     */
    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    /**
     * @return the contentUri
     */
    public String getContentUri() {
        return contentUri;
    }

    /**
     * @param contentUri the contentUri to set
     */
    public void setContentUri(String contentUri) {
        this.contentUri = contentUri;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Statistics total) {
        this.total = total;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
    public void addTag(Tag tag){
        if(tags==null)
            tags = new ArrayList<>();
        tags.add(tag);
    }

    /**
     * @return the taggings
     */
    public List<Tagging> getTaggings() {
        return taggings;
    }

    /**
     * @param taggings the taggings to set
     */
    public void setTaggings(List<Tagging> taggings) {
        this.taggings = taggings;
    }

    /**
     * @return the versions
     */
    public List<Workflow> getVersions() {
        return versions;
    }

    /**
     * @param versions the versions to set
     */
    public void setVersions(List<Workflow> versions) {
        this.versions = versions;
    }

    /**
     * @return the reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
