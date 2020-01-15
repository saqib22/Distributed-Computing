/*
 * Source.cc
 *
 *  Created on: Oct 27, 2019
 *      Author: Faizan E Muhammad
 */
#include<omnetpp.h>

using namespace omnetpp;

class NodeA: public cSimpleModule
{
    private:
        double time_interval;

    protected:
        virtual void initialize();
        virtual void handleMessage(cMessage * msg);
};

class NodeB: public cSimpleModule
{
    private:
        bool load = true;
    protected:
        virtual void handleMessage(cMessage * msg);
};

class NodeC: public cSimpleModule
{
    protected:
        virtual void handleMessage(cMessage * msg);
};

class NodeD: public cSimpleModule
{
    protected:
        virtual void handleMessage(cMessage * msg);
};

Define_Module(NodeA);
Define_Module(NodeB);
Define_Module(NodeC);
Define_Module(NodeD);

void NodeA::initialize()
{
    time_interval= 0.1;
    cMessage *msg= new cMessage();
    scheduleAt(0.01,msg);
}

void NodeA::handleMessage(cMessage * msg)
{
    if(msg->isSelfMessage())
    {
        cMessage * out_msg= new cMessage();
        send(out_msg,"out");
        scheduleAt(simTime()+time_interval,msg);
    }
}

void NodeB::handleMessage(cMessage * msg)
{
    if(load == true){
        send(msg,"out1");
        load = false;
    }
    else{
        send(msg,"out2");
        load = true;
    }
}

void NodeC::handleMessage(cMessage * msg)
{
    delete msg;
}

void NodeD::handleMessage(cMessage * msg)
{
    delete msg;
}
