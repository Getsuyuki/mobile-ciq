using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using AppServices.Models;

namespace AppServices.Core
{
    public class NoSql
    {
        public static readonly NoSql Instance = new NoSql();

        public readonly ConcurrentDictionary<string, DiffusionModel> Items = new ConcurrentDictionary<string, DiffusionModel>();

        private NoSql()
        {
            // Mock up some items, just to start with something
            InitMockedUpItems();
        }

        public DiffusionModel GetItem(string id)
        {
            DiffusionModel model;
            return Items.TryGetValue(id, out model) ? model : null;
        }

        public DiffusionModel SaveItem(DiffusionModel model)
        {
            if (string.IsNullOrEmpty(model.Id))
            {
                AddItem(model);
            }
            else
            {
                 SetItem(model);
            }
            return model;
        }

        public bool RemoveItem(string id)
        {
            DiffusionModel kek;
            return Items.TryRemove(id, out kek);
        }

        public IEnumerable<DiffusionModelDescriptor> GetDescriptors()
        {
            return Items.Select(itm => new DiffusionModelDescriptor
            {
                Id = itm.Value.Id,
                Name = itm.Value.Name
            }).ToList();
        }

        private void AddItem(DiffusionModel model)
        {
            var id = Guid.NewGuid().ToString();
            model.Id = id;
            Items.TryAdd(id, model);
        }

        private void SetItem(DiffusionModel model)
        {
            var modelExist = Items.ContainsKey(model.Id);
            if (!modelExist)
            {
                return;
            }

            Items[model.Id] = model;
        }

        private void InitMockedUpItems()
        {
            string[] names =
            {
                "Umi",
                "Sora",
                "Yuki",
                "Ame",
                "Yomi",
                "Fuu",
                "Tsuki",
                "Ko",
                "Ayumi",
                "Kanna"
            };

            for (var i = 0; i < 10; i++)
            {
                var model = new DiffusionModel
                {
                    Name = names[i],
                    DecayFactor = "96",
                    EstimationMethod = "Exponentially-Weighted Moving Average",
                    Horizon = "1d",
                    Id = Guid.NewGuid().ToString(),
                    Mda = "Left Flat Interpolation",
                    Rfs = string.Format("RFS{0}",i),
                    TimeWindow = ""
                };
                Items.TryAdd(model.Id, model);
            }
        }
    }
}