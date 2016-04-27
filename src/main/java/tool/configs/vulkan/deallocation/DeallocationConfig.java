package tool.configs.vulkan.deallocation;

import tool.configs.Config;

import java.util.ArrayList;

/**
 * Created by constantinos on 18/04/2016.
 */
public class DeallocationConfig extends Config {
    private ArrayList<Config> instances;
    private ArrayList<Config> devices;
    private ArrayList<Config> cmdPools;
    private ArrayList<Config> cmdBuffers;
    private ArrayList<Config> semaphores;
    private ArrayList<Config> events;
    private ArrayList<Config> fences;
    private ArrayList<Config> buffers;
    private ArrayList<Config> bufferViews;
    private ArrayList<Config> images;
    private ArrayList<Config> imageViews;
    private ArrayList<Config> pipelineCaches;
    private ArrayList<Config> computePipelines;
    private ArrayList<Config> graphicsPipelines;
    private ArrayList<Config> pipelineLayouts;
    private ArrayList<Config> shaders;
    private ArrayList<Config> descriptors;

    public DeallocationConfig() {
        instances = new ArrayList<>();
        devices = new ArrayList<>();
        cmdBuffers = new ArrayList<>();
        cmdPools = new ArrayList<>();
        semaphores = new ArrayList<>();
        events = new ArrayList<>();
        fences = new ArrayList<>();
        buffers = new ArrayList<>();
        bufferViews = new ArrayList<>();
        images = new ArrayList<>();
        imageViews = new ArrayList<>();
        pipelineCaches = new ArrayList<>();
        computePipelines = new ArrayList<>();
        graphicsPipelines = new ArrayList<>();
        shaders = new ArrayList<>();
        pipelineLayouts = new ArrayList<>();
        descriptors = new ArrayList<>();
    }

    public ArrayList<Config> getInstances() {
        return instances;
    }

    public void setInstances(ArrayList<Config> instances) {
        this.instances = instances;
    }

    public ArrayList<Config> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Config> devices) {
        this.devices = devices;
    }

    public ArrayList<Config> getCmdPools() {
        return cmdPools;
    }

    public void setCmdPools(ArrayList<Config> cmdPools) {
        this.cmdPools = cmdPools;
    }

    public ArrayList<Config> getCmdBuffers() {
        return cmdBuffers;
    }

    public void setCmdBuffers(ArrayList<Config> cmdBuffers) {
        this.cmdBuffers = cmdBuffers;
    }

    public ArrayList<Config> getSemaphores() {
        return semaphores;
    }

    public void setSemaphores(ArrayList<Config> semaphores) {
        this.semaphores = semaphores;
    }

    public ArrayList<Config> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Config> events) {
        this.events = events;
    }

    public ArrayList<Config> getFences() {
        return fences;
    }

    public void setFences(ArrayList<Config> fences) {
        this.fences = fences;
    }

    public ArrayList<Config> getBuffers() {
        return buffers;
    }

    public void setBuffers(ArrayList<Config> buffers) {
        this.buffers = buffers;
    }

    public ArrayList<Config> getBufferViews() {
        return bufferViews;
    }

    public void setBufferViews(ArrayList<Config> bufferViews) {
        this.bufferViews = bufferViews;
    }

    public ArrayList<Config> getImages() {
        return images;
    }

    public void setImages(ArrayList<Config> images) {
        this.images = images;
    }

    public ArrayList<Config> getImageViews() {
        return imageViews;
    }

    public void setImageViews(ArrayList<Config> imageViews) {
        this.imageViews = imageViews;
    }

    public ArrayList<Config> getPipelineCaches() {
        return pipelineCaches;
    }

    public void setPipelineCaches(ArrayList<Config> pipelineCaches) {
        this.pipelineCaches = pipelineCaches;
    }

    public ArrayList<Config> getComputePipelines() {
        return computePipelines;
    }

    public void setComputePipelines(ArrayList<Config> computePipelines) {
        this.computePipelines = computePipelines;
    }

    public ArrayList<Config> getGraphicsPipelines() {
        return graphicsPipelines;
    }

    public void setGraphicsPipelines(ArrayList<Config> graphicsPipelines) {
        this.graphicsPipelines = graphicsPipelines;
    }

    public ArrayList<Config> getPipelineLayouts() {
        return pipelineLayouts;
    }

    public void setPipelineLayouts(ArrayList<Config> pipelineLayouts) {
        this.pipelineLayouts = pipelineLayouts;
    }

    public ArrayList<Config> getShaders() {
        return shaders;
    }

    public void setShaders(ArrayList<Config> shaders) {
        this.shaders = shaders;
    }

    public ArrayList<Config> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(ArrayList<Config> descriptors) {
        this.descriptors = descriptors;
    }
}
