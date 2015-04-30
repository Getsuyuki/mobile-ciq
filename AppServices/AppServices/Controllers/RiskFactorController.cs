using System.Collections.Generic;
using System.Web.Http;
using AppServices.Core;
using AppServices.Models;

namespace AppServices.Controllers
{
    public class RiskFactorController : ApiController
    {
        [HttpGet]
        public IEnumerable<DiffusionModelDescriptor> DiffusionModelDescriptors()
        {
            return NoSql.Instance.GetDescriptors();
        }

        [HttpGet]
        public DiffusionModel DiffusionModel(string id)
        {
            return NoSql.Instance.GetItem(id);
        }

        [HttpPost]
        public DiffusionModel SaveDiffusionModel([FromBody]DiffusionModel model)
        {
            return NoSql.Instance.SaveItem(model);
        }

        [HttpPost]
        public bool RemoveDiffusionModel([FromBody]string id)
        {
            return NoSql.Instance.RemoveItem(id);
        }

    }
}
