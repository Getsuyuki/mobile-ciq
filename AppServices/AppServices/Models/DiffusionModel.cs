using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppServices.Models
{
    public class DiffusionModel
    {
        public string RFS { get; set; }
        public double Horizon { get; set; }
        public string EstimationMethod { get; set; }
        public int DecayFactor { get; set; }
        public double TimeWindow { get; set; }
        public string MDA { get; set; }
    }
}