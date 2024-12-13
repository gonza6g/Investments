import React from 'react';
import './LastUpdate.css';

function LastUpdate({ timestamp }) {
  if (!timestamp) return null;

  const formatDate = (isoString) => {
    const date = new Date(isoString);
    return new Intl.DateTimeFormat('en-US', {
      dateStyle: 'medium',
      timeStyle: 'medium'
    }).format(date);
  };

  return (
    <div className="last-update">
      Last updated: {formatDate(timestamp)}
    </div>
  );
}

export default LastUpdate; 