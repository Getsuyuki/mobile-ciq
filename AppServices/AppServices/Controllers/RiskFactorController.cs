using System.Dynamic;
using System.Web;
using System.Web.Http;

namespace AppServices.Controllers
{
    public class RiskFactorController : ApiController
    {
        //
        // GET: /RiskFactor/
        [HttpGet]
        public string[] RetreiveSpaces()
        {
            return new string[] { "Umi", "Sora", "Yomi", "Yuki", "Ami" };
        }

        //public ExpandoObject RetrieveSpaces()
        //{
        //    dynamic f = new ExpandoObject();
        //    f.IDs = new string[] { "Umi", "Sora", "Yomi", "Yuki", "Ami" };
        //    return f;
        //}

    }
}
