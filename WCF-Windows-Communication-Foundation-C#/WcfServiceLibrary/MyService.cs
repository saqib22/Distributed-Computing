using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;
using System.ServiceModel;

namespace WcfServiceLibrary
{
    [DataContract]
    public class Maths
    {

        [DataMember]
        public int Number1 { get; set; }
        [DataMember]
        public int Number2 { get; set; }
    }
    [ServiceContract]
    public interface IMaths
    {
        [OperationContract]
        int Addition(Maths obj1);
        [OperationContract]
        int Subtraction(Maths obj2);
        [OperationContract]
        int Multiplication(Maths obj3);
        [OperationContract]
        int Division(Maths obj4);
        [OperationContract]
        int power(Maths obj4);
        [OperationContract]
        int sin(Maths obj4);
        [OperationContract]
        int cos(Maths obj4);
        [OperationContract]
        int log(Maths obj4);


    }
    class MyService : IMaths
    {
        #region IMaths Members
        public int Addition(Maths obj1)
        {
            return (obj1.Number1 + obj1.Number2);
        }
        public int Subtraction(Maths obj2)
        {
            return (obj2.Number1 - obj2.Number2);
        }
        public int Multiplication(Maths obj3)
        {
            return (obj3.Number1 * obj3.Number2);
        }
        public int Division(Maths obj4)
        {
            return (obj4.Number1 / obj4.Number2);
        }

        public int power(Maths obj5)
        {
            return (int)Math.Pow(obj5.Number1, 2.0);
        }

        public int sin(Maths obj)
        {
            return (int)Math.Sin(obj.Number1);
        }

        public int cos(Maths obj)
        {
            return (int)Math.Cos(obj.Number1);
        }

        public int log(Maths obj)
        {
            return (int)Math.Log(obj.Number1);
        }
        #endregion
    }
}
