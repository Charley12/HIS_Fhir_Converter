import React from 'react';

const ChatWindow = () => {
  return (
    <div className="flex flex-col h-full bg-white border-r">
      <div className="p-4 border-b font-semibold">AI Mapping Assistant</div>
      <div className="flex-1 overflow-y-auto p-4 space-y-4">
        <div className="bg-slate-100 p-3 rounded-lg max-w-[80%]">
          Hello! Please upload your source schema to begin mapping to FHIR.
        </div>
      </div>
      <div className="p-4 border-t">
        <div className="flex space-x-2">
          <input
            type="text"
            placeholder="Type your message..."
            className="flex-1 border rounded-md p-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <button className="bg-blue-600 text-white px-4 py-2 rounded-md">Send</button>
        </div>
      </div>
    </div>
  );
};

export default ChatWindow;
