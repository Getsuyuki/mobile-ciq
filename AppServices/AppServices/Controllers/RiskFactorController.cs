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

        [HttpGet]
        public bool RemoveDiffusionModel(string id)
        {
            return NoSql.Instance.RemoveItem(id);
        }

    }
}
