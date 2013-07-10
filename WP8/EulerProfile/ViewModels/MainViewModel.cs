using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using EulerProfile.Resources;
using EulerProfile.Model;
using EulerProfile.Repositories;

namespace EulerProfile.ViewModels
{
    public class MainViewModel: ViewModel
    {
        private UserProfile _me = null;

        /// <summary>
        /// Sample ViewModel property; this property is used in the view to display its value using a Binding
        /// </summary>
        /// <returns></returns>
        public UserProfile Me
        {
            get
            {
                return _me;
            }
            set
            {
                _me = value;
                NotifyPropertyChanged("Me");
            }
        }

        /// <summary>
        /// Sample property that returns a localized string
        /// </summary>
        public string LocalizedSampleProperty
        {
            get
            {
                return AppResources.SampleProperty;
            }
        }

        public bool IsDataLoaded
        {
            get;
            private set;
        }

        public async void LoadData()
        {
            var repo = new ProjectEulerRepository();            
            Me = await repo.GetByUsername("vidstige");

            IsDataLoaded = true;
        }
    }
}