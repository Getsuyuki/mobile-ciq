using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Messaging;
using AppServices.Models;

namespace AppServices.App_Start
{
    public class NoSql
    {
        public static readonly NoSql Instance = new NoSql();

        public readonly ConcurrentDictionary<string, DiffusionModel> _items = new ConcurrentDictionary<string, DiffusionModel>();

        public DiffusionModel GetItem(string id)
        {
            DiffusionModel model;
            _items.TryGetValue(id, out model);
            return _items.TryGetValue(id, out model) ? model : null;
        }

        public bool SaveItem(String id, DiffusionModel model)
        {
            if (id == null)
                return false;
            _items.AddOrUpdate(id, model, (key, oldValue) => model);
            return true;
        }

        public bool RemoveItem(string id)
        {
            DiffusionModel kek;
            return _items.TryRemove(id, out kek);
        }

        public string[] GetNames()
        {
            return _items.Keys.ToArray();
        }
    }
}