from pymongo import MongoClient
from bson.objectid import ObjectId
import os

class AnimalShelter(object):
	""" CRUD operations for Animal collection in MongoDB """
	def __init__(self, username=None, password=None):
		# Initializing the MongoClient. This helps to
		# access the MongoDB databases and collections.

		# Fetch credentials from Environment Variables.
		# This prevents hardcoding sensitive user information in the source code.
		if username is None:
			username = os.getenv('MONGO_USER')
		if password is None:
			password = os.getenv('MONGO_PASS')
			
		# Defensive programming: Check credentials exist before connecting
		if not username or not password:
			raise Exception("Database credentials are required. Set MONGO_USER and MONGO_PASS environment variables.")

		# Allow Host/Port to be configured, with a default address
		HOST = os.getenv('MONGO_HOST', 'nv-desktop-services.apporto.com')
		PORT = int(os.getenv('MONGO_PORT', 33831))
		DB = 'AAC'
		COL = 'animals'
		
		# Initialize Connection
		try:
			self.client = MongoClient('mongodb://%s:%s@%s:%d' % (username,password,HOST,PORT))
			self.database = self.client['%s' % (DB)]
			self.collection = self.database['%s' % (COL)]
		except Exception as e:
			raise Exception(f"Could not connect to MongoDB: {e}")

	# The C in CRUD - Create data
	def create(self, data):
		"""
		Creates a new document in the database.

		Args: 
			data (dict): The data to insert
		Returns:
			bool: True if successful, False otherwise
		"""
		# Defensive programming: Type checking
		if data is not None and isinstance(data, dict):
			try:
				self.collection.insert_one(data) # Insert a single entry of the given data
				return True
			except Exception as e:
				print(f"Error creating document: {e}")
				return False
		else:
			raise Exception("Invalid input: 'data' must be a dictionary and not empty.")

    # R - Read data
	def read(self, query, limit=0, page=0):
		"""
		Queries the database for documents.
		
		Args:
			query (dict): The search criteria
			limit (int): Max number of results to return
			page (int): The page number for pagination
		Returns:
			list: A list of matching documents
		"""
		# Defensive programming: Type checking
		if query is not None and isinstance(query, dict):
			try:
				cursor = self.collection.find(query)

				# Pagination logic
				if limit > 0:
					cursor.limit(limit)

				if page > 0 and limit > 0:
					skip_amount = (page - 1) * limit
					cursor.skip(skip_amount)
				
				return list(cursor) # Search for the number of data containing the query parameter
			except Exception as e:
				print(f"Error reading document: {e}")
		else:
			raise Exception("Invalid input: 'query' must be a dictionary and not empty.")

    # U - Update data
	def update(self, query, updates):
		"""
		Updates one or more documents in the database.
		
		Args:
			query (dict): The search criteria
			updates (dict): Key-value pairs to modify in the matching documents
		Returns:
			int: The number of documents modified
		"""
		if query is not None and updates is not None:
			# Safety: Prevent broad updates with empty query
			if not query:
				raise Exception("Safety error: Update query cannot be empty.")
			
			try:
				result = self.collection.update_many(query, {"$set": updates})
				return result.modified_count
			except Exception as e:
				print(f"Error updating document: {e}")
				return 0
		else:
			raise Exception("Invalid input: 'query' and 'updates' cannot be empty.")

        
    # D - Delete data
	def delete(self, query):
		"""
		Deletes one or more documents in the database.
		
		Args:
			query (dict): The search criteria
		Returns:
			int: The number of documents deleted
		"""
		if query is not None:
			# Safety: Prevent broad deletes with empty query
			if not query:
				raise Exception("Safety Error: Delete query cannot be empty.")
			
			try:
				result = self.collection.delete_many(query)
				return result.deleted_count
			except Exception as e:
				print(f"Error deleting document: {e}")
				return 0
		else:
			raise Exception("Invalid input: 'query' cannot be empty.")
            
