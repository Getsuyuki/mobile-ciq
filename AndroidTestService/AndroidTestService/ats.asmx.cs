using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Script.Services;
using System.Web.Services;

namespace AndroidTestService
{
    /// <summary>
    /// Summary description for ats
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class ats : System.Web.Services.WebService
    {
        public class LoginIn
        {
            public string Password { get; set; }
            public string UserName { get; set; }
        }

        public class RfItem
        {
            public string ItemName { get; set; }
            public string ItemType { get; set; }

            public RfItem(string name, string type)
            {
                ItemName = name;
                ItemType = type;
            }
        }

        public class RfItems
        {
            public List<RfItem> RfItemsList;

            public RfItems ()
            {
                RfItemsList.Add(new RfItem("Ottawa", "EV"));
                RfItemsList.Add(new RfItem("Jerusalem", "BS"));
                RfItemsList.Add(new RfItem("Moscow", "BS"));
                RfItemsList.Add(new RfItem("Beijing", "BS"));
                RfItemsList.Add(new RfItem("Tokyo", "BS"));
                RfItemsList.Add(new RfItem("Washington", "BS"));
                RfItemsList.Add(new RfItem("Berlin", "BS"));
                RfItemsList.Add(new RfItem("Amsterdam", "BS"));
            }
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat=ResponseFormat.Json)]
        public bool Authenticate (LoginIn input)
        {
            bool isSuccess = true;

            string pw = input.Password;
            string id = input.UserName;

            if (!pw.Equals("password") || !id.Equals("jweber"))
            {
                isSuccess = false;
            }

            return isSuccess;
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public RfItems RetrieveRFItems()
        {
            var output = new RfItems();

            return output;
        }
    }
}
//http://www.codeproject.com/Articles/863/Your-first-C-Web-Service
//http://stackoverflow.com/questions/5806220/how-to-connect-to-my-http-localhost-web-server-from-android-emulator-in-eclips
//https://jermdavis.wordpress.com/2014/09/08/json-web-services-when-youre-stuck-in-with-basic-asp-net/
