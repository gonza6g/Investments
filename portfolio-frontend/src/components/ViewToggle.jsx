import React from 'react';

function ViewToggle({ currentView, onViewChange }) {
  const views = [
    { id: 'portfolio', label: 'Portfolio View' },
    { id: 'gains', label: 'Gains View' },
    { id: 'losses', label: 'Losses View' }
  ];

  return (
    <div className="view-toggle">
      {views.map(view => (
        <button 
          key={view.id}
          className={`toggle-button ${currentView === view.id ? 'active' : ''}`}
          onClick={() => onViewChange(view.id)}
        >
          {view.label}
        </button>
      ))}
    </div>
  );
}

export default ViewToggle; 