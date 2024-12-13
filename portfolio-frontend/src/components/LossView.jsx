import React, { useState } from 'react';
import './PortfolioView.css';

function LossView({ portfolio, totalLosses, lastUpdate }) {
  const [sortConfig, setSortConfig] = useState({
    key: 'gains',
    direction: 'ascending'
  });

  const sortData = (key) => {
    let direction = 'ascending';
    if (sortConfig.key === key && sortConfig.direction === 'ascending') {
      direction = 'descending';
    }
    setSortConfig({ key, direction });
  };

  const getSortedData = () => {
    const filteredData = portfolio.filter(asset => asset.gains && asset.gains < 0);
    
    if (!sortConfig.key) return filteredData;

    return [...filteredData].sort((a, b) => {
      let aValue = a[sortConfig.key];
      let bValue = b[sortConfig.key];
      
      if (typeof aValue === 'number' && typeof bValue === 'number') {
        return sortConfig.direction === 'ascending' 
          ? aValue - bValue 
          : bValue - aValue;
      }
      
      aValue = aValue?.toString().toLowerCase() ?? '';
      bValue = bValue?.toString().toLowerCase() ?? '';
      
      return sortConfig.direction === 'ascending'
        ? aValue.localeCompare(bValue)
        : bValue.localeCompare(aValue);
    });
  };

  const getSortIcon = (key) => {
    if (sortConfig.key !== key) return '↕️';
    return sortConfig.direction === 'ascending' ? '↑' : '↓';
  };

  const sortedData = getSortedData();
  
  if (sortedData.length === 0) {
    return (
      <div className="no-gains-message">
        <p>No assets with losses found.</p>
      </div>
    );
  }

  return (
    <>
      <div className="last-update">
        Last Updated: {lastUpdate || 'Never updated'}
      </div>
      <div className="total-amount">
        Total Losses: <span className="value-negative">${Math.abs(totalLosses).toLocaleString('en-US', { minimumFractionDigits: 2 })}</span>
      </div>
      <table className="portfolio-table">
        <thead>
          <tr>
            <th onClick={() => sortData('symbol')}>
              Symbol {getSortIcon('symbol')}
            </th>
            <th onClick={() => sortData('name')}>
              Name {getSortIcon('name')}
            </th>
            <th onClick={() => sortData('country')}>
              Country {getSortIcon('country')}
            </th>
            <th onClick={() => sortData('industry')}>
              Industry {getSortIcon('industry')}
            </th>
            <th onClick={() => sortData('holdings')}>
              Holdings {getSortIcon('holdings')}
            </th>
            <th onClick={() => sortData('gains')}>
              Losses ($) {getSortIcon('gains')}
            </th>
          </tr>
        </thead>
        <tbody>
          {sortedData.map((asset, index) => (
            <tr key={index}>
              <td className="symbol">{asset.symbol}</td>
              <td>
                <div className="asset-name">{asset.name}</div>
              </td>
              <td>{asset.country}</td>
              <td>{asset.industry}</td>
              <td className="holdings">
                {asset.holdings.toLocaleString('en-US', { minimumFractionDigits: 2 })}
              </td>
              <td className="value-negative">
                ${Math.abs(asset.gains).toLocaleString('en-US', { minimumFractionDigits: 2 })}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default LossView; 