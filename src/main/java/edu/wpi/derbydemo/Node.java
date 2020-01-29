package edu.wpi.derbydemo;

public class Node {
    public String nodeId;
    public int xcoord;
    public int ycoord;
    public int floor;
    public String building;
    public String nodeType;
    public String longName;
    public String shortName;
    public String teamAssigned;

    public Node(){}
    public Node(String nodeId, int xcoord, int ycoord, int floor, String building, String nodeType, String longName,
                String shortName, String teamAssigned){
        this.nodeId=nodeId;
        this.xcoord=xcoord;
        this.ycoord=ycoord;
        this.floor=floor;
        this.building=building;
        this.nodeType=nodeType;
        this.longName=longName;
        this.shortName=shortName;
        this.teamAssigned=teamAssigned;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTeamAssigned() {
        return teamAssigned;
    }

    public void setTeamAssigned(String teamAssigned) {
        this.teamAssigned = teamAssigned;
    }
}
