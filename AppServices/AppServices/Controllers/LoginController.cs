using System;
using System.Dynamic;
using System.Web;
using System.Web.Http;
using AppServices.Models;

namespace AppServices.Controllers
{
    public class LoginController : ApiController
    {
        [HttpPost]
        public bool Authenticate([FromBody]LoginIn input)
        {
            bool isSuccess = true;

            string pw = input.Password;
            string id = input.UserName;

            if (!(pw.Equals("password") && id.Equals("jweber")))
            {
                throw new HttpException(500, "Unauthorized");
            }

            return isSuccess;
        }

        [HttpGet]
        public ExpandoObject AuthenticateX()
        {
            dynamic f = new ExpandoObject();
            f.name = "shlomy";
            return f;
        }
    }
}