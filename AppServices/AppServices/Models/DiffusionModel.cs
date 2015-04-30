namespace AppServices.Models
{
    public class DiffusionModel
    {
        public string Id { get; set; }

        public string Name { get; set; }

        public string Rfs { get; set; }

        public string Horizon { get; set; }

        public string EstimationMethod { get; set; }

        public string DecayFactor { get; set; }

        public string TimeWindow { get; set; }

        public string Mda { get; set; }
    }
}